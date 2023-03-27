package com.example.drinkapp.domain.repository

import com.example.drinkapp.domain.model.Drink
import com.example.drinkapp.domain.model.Ingredient

interface LocalDataSource {

    suspend fun getSelectedDrink(drinkId: Int): Drink

    //suspend fun getSelectedIngredient(ingredientId: Int): Ingredient

}