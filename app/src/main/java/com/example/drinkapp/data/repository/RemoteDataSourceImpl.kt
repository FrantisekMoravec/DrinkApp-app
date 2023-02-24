package com.example.drinkapp.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.drinkapp.data.local.DrinkDatabase
import com.example.drinkapp.data.paging_source.DrinkRemoteMediator
import com.example.drinkapp.data.paging_source.SearchDrinksSource
import com.example.drinkapp.data.remote.DrinkApi
import com.example.drinkapp.domain.model.Drink
import com.example.drinkapp.domain.repository.RemoteDataSource
import com.example.drinkapp.util.Constants.DRINK_ITEMS_PER_PAGE
import kotlinx.coroutines.flow.Flow

@ExperimentalPagingApi
class RemoteDataSourceImpl(
    private val drinkApi: DrinkApi,
//    private val ingredientApi: IngredientApi,
    private val drinkDatabase: DrinkDatabase
): RemoteDataSource {

    private val drinkDao = drinkDatabase.drinkDao()

    override fun getAllDrinks(): Flow<PagingData<Drink>> {
        val pagingSourceFactory = { drinkDao.getAllDrinks() }
        return Pager(
            config = PagingConfig(pageSize = DRINK_ITEMS_PER_PAGE),
            remoteMediator = DrinkRemoteMediator(
                drinkApi = drinkApi,
                drinkDatabase = drinkDatabase
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    override fun searchDrinks(query: String): Flow<PagingData<Drink>> {
        return Pager(
            config = PagingConfig(pageSize = DRINK_ITEMS_PER_PAGE),
            pagingSourceFactory = {
                SearchDrinksSource(
                    drinkApi = drinkApi,
                    query = query
                )
            }
        ).flow
    }
/*
    override fun getAllIngredients(): Flow<PagingData<Ingredient>> {
        TODO("Not yet implemented")
    }

    override fun searchIngredients(query: String): Flow<PagingData<Ingredient>> {
        TODO("Not yet implemented")
    }
*/
}