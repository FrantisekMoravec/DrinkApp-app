package com.example.drinkapp.domain.repository

import com.example.drinkapp.domain.model.Drink

interface LocalDataSource {
    suspend fun getSelectedDrink(drinkId: Int): Drink
}