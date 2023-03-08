package com.example.drinkapp.domain.repository

import androidx.paging.PagingData
import com.example.drinkapp.domain.model.Drink
import com.example.drinkapp.domain.model.Ingredient
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {

    fun getAllDrinks(): Flow<PagingData<Drink>>

    fun searchDrinks(query: String): Flow<PagingData<Drink>>

    fun getAllIngredients(): Flow<PagingData<Ingredient>>

    fun searchIngredients(query: String): Flow<PagingData<Ingredient>>
}