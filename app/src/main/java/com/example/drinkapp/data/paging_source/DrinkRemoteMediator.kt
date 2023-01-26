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
import javax.inject.Inject

@ExperimentalPagingApi
class DrinkRemoteMediator @Inject constructor(
    private val drinkApi: DrinkApi,
    private val drinkDatabase: DrinkDatabase
) : RemoteMediator<Int, Drink>() {

    private val drinkDao = drinkDatabase.drinkDao()
    private val drinkRemoteKeysDao = drinkDatabase.drinkRemoteKeysDao()

    override suspend fun load(loadType: LoadType, state: PagingState<Int, Drink>): RemoteMediator.MediatorResult {
        return try {
            val page = when (loadType) {
                /** načte první stránku */
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextPage?.minus(1) ?: 1
                }
                /** načte předchozí stránku */
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeysForFirstItem(state)
                    val prevPage = remoteKeys?.prevPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    prevPage
                }
                /** načte další stránku */
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKeys?.nextPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    nextPage
                }
            }

            val response = drinkApi.getAllDrinks(page = page)
            if (response.drinks.isNotEmpty()){
                drinkDatabase.withTransaction {
                    if (loadType == LoadType.REFRESH){
                        drinkDao.deleteAllDrinks()
                        drinkRemoteKeysDao.deleteAllRemoteKeys()
                    }
                    val prevPage = response.prevPage
                    val nextPage = response.nextPage
                    val keys = response.drinks.map { drink ->
                        DrinkRemoteKeys(
                            id = drink.id,
                            prevPage = prevPage,
                            nextPage = nextPage
                        )
                    }
                    drinkRemoteKeysDao.addAllRemoteKeys(drinkRemoteKeys = keys)
                    drinkDao.addDrinks(drinks = response.drinks)
                }
            }
            MediatorResult.Success(endOfPaginationReached =  response.nextPage == null)
        }catch (e: Exception){
            return MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, Drink>
    ): DrinkRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                drinkRemoteKeysDao.getRemoteKeys(drinkId = id)
            }
        }
    }

    private suspend fun getRemoteKeysForFirstItem(
        state: PagingState<Int, Drink>
    ): DrinkRemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { drink ->
                drinkRemoteKeysDao.getRemoteKeys(drinkId = drink.id)
            }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, Drink>
    ): DrinkRemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { drink ->
                drinkRemoteKeysDao.getRemoteKeys(drinkId = drink.id)
            }
    }
}