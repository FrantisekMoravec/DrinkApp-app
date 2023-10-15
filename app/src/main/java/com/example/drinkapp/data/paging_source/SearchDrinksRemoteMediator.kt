package com.example.drinkapp.data.paging_source

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.drinkapp.data.local.DrinkDatabase
import com.example.drinkapp.data.remote.DrinkApi
import com.example.drinkapp.domain.model.Drink
import com.example.drinkapp.domain.model.DrinkRemoteKeys

@ExperimentalPagingApi
class SearchDrinksRemoteMediator(
    private val drinkApi: DrinkApi,
    private val drinkDatabase: DrinkDatabase,
    private val query: String
) : RemoteMediator<Int, Drink>() {
    private val drinkDao = drinkDatabase.drinkDao()
    private val drinkRemoteKeysDao = drinkDatabase.drinkRemoteKeysDao()

    override suspend fun load(loadType: LoadType, state: PagingState<Int, Drink>): MediatorResult {
        var endOfPaginationReached = false

        return try {
            val drinkResponses = drinkApi.searchDrinks(name = query)

            if (!drinkResponses.isNullOrEmpty()){
                drinkResponses.forEach { drinkResponse ->
                    drinkDatabase.withTransaction {
                        val drinkPrevPage = drinkResponse.prevPage
                        val drinkNextPage = drinkResponse.nextPage
                        val drinkKeys = drinkResponse.drinks.map { drink ->
                            DrinkRemoteKeys(
                                id = drink.id,
                                prevPage = drinkPrevPage,
                                nextPage = drinkNextPage,
                                lastUpdated = drinkResponse.lastUpdated
                            )
                        }
                        drinkRemoteKeysDao.addAllDrinkRemoteKeys(drinkRemoteKeys = drinkKeys)
                        drinkDao.addDrinks(drinks = drinkResponse.drinks)
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