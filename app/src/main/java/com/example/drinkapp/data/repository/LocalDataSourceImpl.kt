package com.example.drinkapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.drinkapp.data.local.DrinkDatabase
import com.example.drinkapp.domain.model.Drink
import com.example.drinkapp.domain.model.Ingredient
import com.example.drinkapp.domain.repository.LocalDataSource
import com.example.drinkapp.util.Constants
import kotlinx.coroutines.flow.Flow

class LocalDataSourceImpl(
    drinkDatabase: DrinkDatabase
): LocalDataSource {
    private val drinkDao = drinkDatabase.drinkDao()
    private val ingredientDao = drinkDatabase.ingredientDao()

    override suspend fun getSelectedDrink(drinkId: Int): Drink {
        return drinkDao.getSelectedDrink(drinkId = drinkId)
    }

    override suspend fun getAllLocalDrinks(): Flow<List<Drink>> {
        return drinkDao.getAllLocalDrinks()
    }

    override suspend fun getAllIngredientsMadeByUser(ingredientMadeByUser: Boolean): Ingredient {
        return ingredientDao.getIngredientsMadeByUser(ingredientMadeByUser = ingredientMadeByUser)
    }

    override fun getDrinksContainingIngredients(ingredientNames: List<String>): Flow<PagingData<Drink>> {
        return Pager(
            config = PagingConfig(pageSize = Constants.DRINK_ITEMS_PER_PAGE),
            pagingSourceFactory = { drinkDao.getDrinksContainingIngredients(ingredientNames) }
        ).flow
    }
}
    /*
    override suspend fun getSelectedIngredients(ingredientIds: List<Ingredient>): List<Ingredient> {
        return ingredientDao.getSelectedIngredients(ingredientIds = ingredientIds)
    }
    */

    /*
        override suspend fun getSelectedIngredient(ingredientId: Int): Ingredient {
            return ingredientDao.getSelectedIngredient(ingredientId = ingredientId)
        }
    */