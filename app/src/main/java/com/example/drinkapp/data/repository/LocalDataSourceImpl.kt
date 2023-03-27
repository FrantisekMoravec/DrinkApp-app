package com.example.drinkapp.data.repository

import com.example.drinkapp.data.local.DrinkDatabase
import com.example.drinkapp.domain.model.Drink
import com.example.drinkapp.domain.model.Ingredient
import com.example.drinkapp.domain.repository.LocalDataSource

class LocalDataSourceImpl(
    drinkDatabase: DrinkDatabase
): LocalDataSource {
    private val drinkDao = drinkDatabase.drinkDao()
    private val ingredientDao = drinkDatabase.ingredientDao()

    override suspend fun getSelectedDrink(drinkId: Int): Drink {
        return drinkDao.getSelectedDrink(drinkId = drinkId)
    }
/*
    override suspend fun getSelectedIngredient(ingredientId: Int): Ingredient {
        return ingredientDao.getSelectedIngredient(ingredientId = ingredientId)
    }
    */
}