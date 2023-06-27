package com.example.drinkapp.data.paging_source

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.drinkapp.data.local.DrinkDatabase
import com.example.drinkapp.data.remote.IngredientFamilyApi
import com.example.drinkapp.domain.model.IngredientFamily
import com.example.drinkapp.domain.model.IngredientFamilyRemoteKeys

@ExperimentalPagingApi
class IngredientFamilyRemoteMediator (
    private val ingredientFamilyApi: IngredientFamilyApi,
    private val drinkDatabase: DrinkDatabase
) : RemoteMediator<Int, IngredientFamily>(){
    private val ingredientDao = drinkDatabase.ingredientDao()
    private val ingredientFamilyRemoteKeysDao = drinkDatabase.ingredientFamilyRemoteKeysDao()

    override suspend fun initialize(): InitializeAction {
        val currentTime = System.currentTimeMillis()
        val lastUpdated = ingredientFamilyRemoteKeysDao.getIngredientFamilyRemoteKeys(ingredientFamilyId = 1)?.lastUpdated ?: 0L
        val cacheTimeout = 1440
        val diffInMinutes = (currentTime - lastUpdated) / 1000 / 60

        return if (diffInMinutes.toInt() <= cacheTimeout){
            InitializeAction.SKIP_INITIAL_REFRESH
        }else{
            InitializeAction.LAUNCH_INITIAL_REFRESH
        }
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, IngredientFamily>
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

            val ingredientFamilyResponse = ingredientFamilyApi.getAllIngredientFamilies(page = page)

            if (ingredientFamilyResponse.ingredientFamilies.isNotEmpty()){
                drinkDatabase.withTransaction {
                    if (loadType == LoadType.REFRESH){
                        ingredientDao.deleteAllIngredientFamilies()
                        ingredientFamilyRemoteKeysDao.deleteAllIngredientFamilyRemoteKeys()
                    }
                    val ingredientFamilyPrevPage = ingredientFamilyResponse.prevPage
                    val ingredientFamilyNextPage = ingredientFamilyResponse.nextPage
                    val ingredientFamilyKeys = ingredientFamilyResponse.ingredientFamilies.map { ingredientFamily ->
                        IngredientFamilyRemoteKeys(
                            id = ingredientFamily.id,
                            prevPage = ingredientFamilyPrevPage,
                            nextPage = ingredientFamilyNextPage,
                            lastUpdated = ingredientFamilyResponse.lastUpdated
                        )
                    }
                    ingredientFamilyRemoteKeysDao.addAllIngredientFamilyRemoteKeys(ingredientFamilyRemoteKeys = ingredientFamilyKeys)
                    ingredientDao.addIngredientFamilies(ingredientFamilies = ingredientFamilyResponse.ingredientFamilies)
                }
            }
            MediatorResult.Success(endOfPaginationReached = ingredientFamilyResponse.nextPage == null)
        }catch (e: Exception){
            return MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, IngredientFamily>
    ): IngredientFamilyRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                ingredientFamilyRemoteKeysDao.getIngredientFamilyRemoteKeys(ingredientFamilyId = id)
            }
        }
    }

    private suspend fun getRemoteKeysForFirstItem(
        state: PagingState<Int, IngredientFamily>
    ): IngredientFamilyRemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { ingredientFamily ->
                ingredientFamilyRemoteKeysDao.getIngredientFamilyRemoteKeys(ingredientFamilyId = ingredientFamily.id)
            }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, IngredientFamily>
    ): IngredientFamilyRemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { ingredientFamily ->
                ingredientFamilyRemoteKeysDao.getIngredientFamilyRemoteKeys(ingredientFamilyId = ingredientFamily.id)
            }
    }
}