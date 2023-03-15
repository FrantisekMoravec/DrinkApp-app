package com.example.drinkapp.data.repository

import com.example.drinkapp.data.local.DrinkDatabase
import com.example.drinkapp.domain.model.Drink
import com.example.drinkapp.domain.repository.LocalDataSource

class LocalDataSourceImpl(
    drinkDatabase: DrinkDatabase
): LocalDataSource {
    private val drinkDao = drinkDatabase.drinkDao()

    override suspend fun getSelectedDrink(drinkId: Int): Drink {
        return drinkDao.getSelectedDrink(drinkId = drinkId)
    }
}