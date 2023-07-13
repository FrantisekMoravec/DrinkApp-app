package com.example.drinkapp.domain.repository

import androidx.paging.PagingData
import com.example.drinkapp.domain.model.Drink
import com.example.drinkapp.domain.model.Ingredient
import com.example.drinkapp.domain.model.IngredientFamily
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {

    fun getAllRemoteDrinks(): Flow<PagingData<Drink>>

    fun searchDrinks(query: String): Flow<PagingData<Drink>>

    fun getAllIngredients(): Flow<PagingData<Ingredient>>

    fun searchIngredients(query: String): Flow<PagingData<Ingredient>>

    fun getAllIngredientFamilies(): Flow<PagingData<IngredientFamily>>

    fun searchIngredientFamilies(query: String): Flow<PagingData<IngredientFamily>>

    //posílá se několik parametrů(jmen ingrediencí) ale jsou v jednom stringu
    fun getDrinksContainingIngredients(ingredientNames: List<String>): Flow<PagingData<Drink>>

    fun searchIngredientsByIngredientFamilyName(ingredientFamilyName: String): Flow<PagingData<Ingredient>>

    //fun getSelectedRemoteDrink(drinkId: Int): Drink
}