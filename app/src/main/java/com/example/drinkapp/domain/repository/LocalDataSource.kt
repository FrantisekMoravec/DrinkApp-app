package com.example.drinkapp.domain.repository

import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.example.drinkapp.domain.model.Drink
import com.example.drinkapp.domain.model.Ingredient
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {

    suspend fun getSelectedDrink(drinkId: Int): Drink
    suspend fun getAllIngredientsMadeByUser(madeByUser: Boolean): Ingredient

    fun getDrinksContainingIngredients(ingredientNames: List<String>): Flow<PagingData<Drink>>
}
    //suspend fun getSelectedIngredients(ingredientIds: List<Int>): List<Ingredient>

    //suspend fun getSelectedIngredient(ingredientId: Int): Ingredient