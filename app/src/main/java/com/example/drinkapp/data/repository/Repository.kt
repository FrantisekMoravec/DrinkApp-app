package com.example.drinkapp.data.repository

import androidx.paging.PagingData
import com.example.drinkapp.domain.model.Drink
import com.example.drinkapp.domain.model.Ingredient
import com.example.drinkapp.domain.repository.LocalDataSource
import com.example.drinkapp.domain.repository.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val remote: RemoteDataSource,
    private val local: LocalDataSource
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

    suspend fun getSelectedDrink(drinkId: Int): Drink{
        return local.getSelectedDrink(drinkId = drinkId)
    }

}