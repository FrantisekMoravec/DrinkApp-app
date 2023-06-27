package com.example.drinkapp.domain.repository

import androidx.paging.PagingData
import com.example.drinkapp.domain.model.Drink
import com.example.drinkapp.domain.model.Ingredient
import com.example.drinkapp.domain.model.IngredientFamily
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {

    suspend fun getSelectedLocalDrink(drinkId: Int): Drink

    suspend fun getAllLocalDrinks(): Flow<List<Drink>>

    suspend fun getSelectedIngredientsByName(ingredientNames: List<String>): List<Ingredient>
/*
    suspend fun getAllIngredientsMadeByUser(madeByUser: Boolean): Ingredient
*/
    fun getDrinksContainingIngredients(ingredientFamilyNames: List<String>, ingredientFamilyNamesCount: Int): Flow<PagingData<Drink>>

    suspend fun getSelectedIngredientFamiliesByName(ingredientFamilyNames: List<String>): List<IngredientFamily>
}
    //suspend fun getSelectedIngredients(ingredientIds: List<Int>): List<Ingredient>

    //suspend fun getSelectedIngredientById(ingredientId: Int): Ingredient