package com.example.drinkapp.data.paging_source

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.drinkapp.data.local.DrinkDatabase
import com.example.drinkapp.data.remote.DrinkApi
import com.example.drinkapp.domain.model.Drink
import com.example.drinkapp.domain.model.DrinkRemoteKeys
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@ExperimentalPagingApi
class DrinkRemoteMediator(
    private val drinkApi: DrinkApi,
    private val drinkDatabase: DrinkDatabase
) : RemoteMediator<Int, Drink>() {

    private val drinkDao = drinkDatabase.drinkDao()
    private val drinkRemoteKeysDao = drinkDatabase.drinkRemoteKeysDao()

    /** po 24 hodinách se při dalším spojením se serverem znovu stahnou data ze serveru a nahradí data lokální */
    override suspend fun initialize(): InitializeAction {
        val currentTime = System.currentTimeMillis()
        val lastUpdated = drinkRemoteKeysDao.getRemoteKeys(drinkId = 1)?.lastUpdated ?: 0L
        val cacheTimeout = 1440
        //Log.d("RemoteMediator", "Current Time: ${parseMillis(currentTime)}")
        //Log.d("RemoteMediator", "Last Updated Time: ${parseMillis(lastUpdated)}")

        val diffInMinutes = (currentTime - lastUpdated) / 1000 / 60
        return  if (diffInMinutes.toInt() <= cacheTimeout){
            //Log.d("RemoteMediator", "up to date")
            InitializeAction.SKIP_INITIAL_REFRESH
        }else{
            //Log.d("RemoteMediator", "refresh")
            InitializeAction.LAUNCH_INITIAL_REFRESH
        }
    }

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
                            nextPage = nextPage,
                            lastUpdated = response.lastUpdated
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
/*
    private fun parseMillis(millis: Long): String{
        val date = Date(millis)
        val format = SimpleDateFormat("yyyy.MM.dd HH:mm", Locale.ROOT)
        return format.format(date)
    }
*/
}