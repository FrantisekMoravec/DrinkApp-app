package com.example.drinkapp.data.paging_source

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.drinkapp.data.local.DrinkDatabase
import com.example.drinkapp.data.remote.IngredientFamilyApi
import com.example.drinkapp.domain.model.Drink
import com.example.drinkapp.domain.model.IngredientFamily
import com.example.drinkapp.domain.model.IngredientFamilyRemoteKeys

@ExperimentalPagingApi
class SearchIngredientFamilyRemoteMediator (
    private val ingredientFamilyApi: IngredientFamilyApi,
    private val drinkDatabase: DrinkDatabase,
    private val query: String
) : RemoteMediator<Int, IngredientFamily>() {
    private val ingredientDao = drinkDatabase.ingredientDao()
    private val ingredientFamilyRemoteKeysDao = drinkDatabase.ingredientFamilyRemoteKeysDao()

    override suspend fun load(loadType: LoadType, state: PagingState<Int, IngredientFamily>): MediatorResult {
        var endOfPaginationReached = false

        return try {
            val ingredientFamilyResponses = ingredientFamilyApi.searchIngredientFamilies(name = query)

            if (!ingredientFamilyResponses.isNullOrEmpty()){
                ingredientFamilyResponses.forEach { ingredientFamilyResponse ->
                    drinkDatabase.withTransaction {
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
            }

            endOfPaginationReached = true
            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        }catch (e: Exception){
            return MediatorResult.Error(e)
        }
    }
}