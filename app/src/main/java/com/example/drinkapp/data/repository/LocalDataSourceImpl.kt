package com.example.drinkapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.drinkapp.data.local.DrinkDatabase
import com.example.drinkapp.domain.model.Drink
import com.example.drinkapp.domain.model.Ingredient
import com.example.drinkapp.domain.repository.LocalDataSource
import kotlinx.coroutines.flow.Flow

class LocalDataSourceImpl(
    drinkDatabase: DrinkDatabase
): LocalDataSource {
    private val drinkDao = drinkDatabase.drinkDao()
    private val ingredientDao = drinkDatabase.ingredientDao()

    override suspend fun getSelectedLocalDrink(drinkId: Int): Drink {
        return drinkDao.getSelectedLocalDrink(drinkId = drinkId)
    }

    override suspend fun getSelectedIngredientsByName(ingredientNames: List<String>): List<Ingredient> {
        return ingredientDao.getSelectedIngredientsByName(ingredientNames = ingredientNames)
    }

    override suspend fun getAllLocalDrinks(): Flow<List<Drink>> {
        return drinkDao.getAllLocalDrinks()
    }

    override suspend fun getAllIngredientsMadeByUser(ingredientMadeByUser: Boolean): Ingredient {
        return ingredientDao.getIngredientsMadeByUser(ingredientMadeByUser = ingredientMadeByUser)
    }

    override fun getDrinksContainingIngredients(ingredientNames: List<String>, ingredientNamesCount: Int): Flow<PagingData<Drink>> {
        return Pager(
            config = PagingConfig(pageSize = 21),
            pagingSourceFactory = { drinkDao.getDrinksContainingIngredients(ingredientNames, ingredientNamesCount) }
        ).flow

    }
}