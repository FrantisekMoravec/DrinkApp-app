package com.example.drinkapp.data.repository

import androidx.paging.PagingData
import com.example.drinkapp.domain.model.Drink
import com.example.drinkapp.domain.model.Ingredient
import com.example.drinkapp.domain.repository.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val remote: RemoteDataSource
){

    fun getAllDrinks(): Flow<PagingData<Drink>>{
        return remote.getAllDrinks()
    }

    fun searchDrinks(query: String): Flow<PagingData<Drink>>{
        return remote.searchDrinks(query = query)
    }

    fun getAllIngredients(): Flow<PagingData<Ingredient>>{
        return remote.getAllIngredients()
    }

    fun searchIngredients(query: String): Flow<PagingData<Ingredient>>{
        return remote.searchIngredients(query = query)
    }

}