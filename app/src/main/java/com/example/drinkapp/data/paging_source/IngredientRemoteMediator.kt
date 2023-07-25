package com.example.drinkapp.data.paging_source

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.drinkapp.data.local.DrinkDatabase
import com.example.drinkapp.data.remote.IngredientApi
import com.example.drinkapp.domain.model.Drink
import com.example.drinkapp.domain.model.DrinkRemoteKeys
import com.example.drinkapp.domain.model.Ingredient
import com.example.drinkapp.domain.model.IngredientRemoteKeys

@ExperimentalPagingApi
class IngredientRemoteMediator (
    private val ingredientApi: IngredientApi,
    private val drinkDatabase: DrinkDatabase
) : RemoteMediator<Int, Ingredient>(){
    private val ingredientDao = drinkDatabase.ingredientDao()
    private val ingredientRemoteKeysDao = drinkDatabase.ingredientRemoteKeysDao()

    override suspend fun initialize(): InitializeAction {
        val currentTime = System.currentTimeMillis()
        val lastUpdated = ingredientRemoteKeysDao.getIngredientRemoteKeys(ingredientId = 1)?.lastUpdated ?: 0L
        val cacheTimeout = 1440
        val diffInMinutes = (currentTime - lastUpdated) / 1000 / 60

        return  if (diffInMinutes.toInt() <= cacheTimeout){
            InitializeAction.SKIP_INITIAL_REFRESH
        }else{
            InitializeAction.LAUNCH_INITIAL_REFRESH
        }
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Ingredient>
    ): MediatorResult {
        return try {
            val page = when(loadType){
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextPage?.minus(1) ?: 1
                }
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeysForFirstItem(state)
                    val prevPage = remoteKeys?.prevPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    prevPage
                }
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKeys?.nextPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    nextPage
                }
            }

            var endOfPaginationReached = false
            //val ingredientResponses = ingredientApi.getAllIngredients(page = page)
            val ingredientResponse = ingredientApi.getAllIngredients(page = page)

            if (ingredientResponse.ingredients.isNotEmpty()){
                drinkDatabase.withTransaction {
                    if (loadType == LoadType.REFRESH){
                        ingredientDao.deleteAllIngredients()
                        ingredientRemoteKeysDao.deleteAllIngredientRemoteKeys()
                    }
                    val ingredientPrevPage = ingredientResponse.prevPage
                    val ingredientNextPage = ingredientResponse.nextPage
                    val ingredientKeys = ingredientResponse.ingredients.map { ingredient ->
                        IngredientRemoteKeys(
                            id = ingredient.ingredientId,
                            prevPage = ingredientPrevPage,
                            nextPage = ingredientNextPage,
                            lastUpdated = ingredientResponse.lastUpdated
                        )
                    }
                    ingredientRemoteKeysDao.addAllIngredientRemoteKeys(ingredientRemoteKeys = ingredientKeys)
                    ingredientDao.addIngredients(ingredients = ingredientResponse.ingredients)
                }
            }

            endOfPaginationReached = true
            MediatorResult.Success(endOfPaginationReached = (ingredientResponse.nextPage == null) && endOfPaginationReached)
        }catch (e: Exception){
            return MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, Ingredient>
    ): IngredientRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.ingredientId?.let { id ->
                ingredientRemoteKeysDao.getIngredientRemoteKeys(ingredientId = id)
            }
        }
    }

    private suspend fun getRemoteKeysForFirstItem(
        state: PagingState<Int, Ingredient>
    ): IngredientRemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { ingredient ->
                ingredientRemoteKeysDao.getIngredientRemoteKeys(ingredientId = ingredient.ingredientId)
            }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, Ingredient>
    ): IngredientRemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { ingredient ->
                ingredientRemoteKeysDao.getIngredientRemoteKeys(ingredientId = ingredient.ingredientId)
            }
    }
}