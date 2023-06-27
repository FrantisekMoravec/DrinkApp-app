package com.example.drinkapp.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.drinkapp.data.local.DrinkDatabase
import com.example.drinkapp.data.paging_source.DrinkRemoteMediator
import com.example.drinkapp.data.paging_source.IngredientFamilyRemoteMediator
import com.example.drinkapp.data.paging_source.IngredientRemoteMediator
import com.example.drinkapp.data.paging_source.SearchDrinksSource
import com.example.drinkapp.data.paging_source.SearchIngredientFamiliesSource
import com.example.drinkapp.data.paging_source.SearchIngredientsSource
import com.example.drinkapp.data.remote.DrinkApi
import com.example.drinkapp.data.remote.IngredientApi
import com.example.drinkapp.data.remote.IngredientFamilyApi
import com.example.drinkapp.domain.model.Drink
import com.example.drinkapp.domain.model.Ingredient
import com.example.drinkapp.domain.model.IngredientFamily
import com.example.drinkapp.domain.repository.RemoteDataSource
import com.example.drinkapp.util.Constants.DRINK_ITEMS_PER_PAGE
import com.example.drinkapp.util.Constants.INGREDIENT_FAMILY_ITEMS_PER_PAGE
import com.example.drinkapp.util.Constants.INGREDIENT_ITEMS_PER_PAGE
import kotlinx.coroutines.flow.Flow

/** knihovna paging bere data z databáze a upravuje si je jak potřebuje */
@ExperimentalPagingApi
class RemoteDataSourceImpl(
    private val drinkApi: DrinkApi,
    private val ingredientApi: IngredientApi,
    private val ingredientFamilyApi: IngredientFamilyApi,
    private val drinkDatabase: DrinkDatabase
): RemoteDataSource {

    private val drinkDao = drinkDatabase.drinkDao()
    private val ingredientDao = drinkDatabase.ingredientDao()

    override fun getAllRemoteDrinks(): Flow<PagingData<Drink>> {
        val pagingSourceFactory = { drinkDao.getAllRemoteDrinks() }
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

    override fun getAllIngredients(): Flow<PagingData<Ingredient>> {
        val pagingSourceFactory = { ingredientDao.getAllIngredients() }
        return Pager(
            config = PagingConfig(pageSize = INGREDIENT_ITEMS_PER_PAGE),
            remoteMediator = IngredientRemoteMediator(
                ingredientApi = ingredientApi,
                drinkDatabase = drinkDatabase
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    override fun searchIngredients(query: String): Flow<PagingData<Ingredient>> {
        return Pager(
            config = PagingConfig(pageSize = INGREDIENT_ITEMS_PER_PAGE),
            pagingSourceFactory = {
                SearchIngredientsSource(
                    ingredientApi = ingredientApi,
                    query = query
                )
            }
        ).flow
    }

    override fun getAllIngredientFamilies(): Flow<PagingData<IngredientFamily>> {
        val pagingSourceFactory = { ingredientDao.getAllIngredientFamilies() }
        return Pager(
            config = PagingConfig(pageSize = INGREDIENT_FAMILY_ITEMS_PER_PAGE),
            remoteMediator = IngredientFamilyRemoteMediator(
                ingredientFamilyApi = ingredientFamilyApi,
                drinkDatabase = drinkDatabase
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    override fun searchIngredientFamilies(query: String): Flow<PagingData<IngredientFamily>> {
        return Pager(
            config = PagingConfig(pageSize = INGREDIENT_FAMILY_ITEMS_PER_PAGE),
            pagingSourceFactory = {
                SearchIngredientFamiliesSource(
                    ingredientFamilyApi = ingredientFamilyApi,
                    query = query
                )
            }
        ).flow
    }

/*
    override fun getSelectedRemoteDrink(drinkId: Int): Drink {
        return
    }
    */
}