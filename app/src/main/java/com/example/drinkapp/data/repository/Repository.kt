package com.example.drinkapp.data.repository

import androidx.paging.PagingData
import com.example.drinkapp.domain.model.Drink
import com.example.drinkapp.domain.model.Ingredient
import com.example.drinkapp.domain.model.IngredientFamily
import com.example.drinkapp.domain.repository.LocalDataSource
import com.example.drinkapp.domain.repository.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val remote: RemoteDataSource,
    private val local: LocalDataSource
){
    fun getAllRemoteDrinks(): Flow<PagingData<Drink>>{
        return remote.getAllRemoteDrinks()
    }

    suspend fun getAllLocalDrinks(): Flow<List<Drink>>{
        return local.getAllLocalDrinks()
    }

    fun searchDrinks(query: String): Flow<PagingData<Drink>>{
        return remote.searchDrinks(query = query)
    }

    fun getAllIngredients(): Flow<PagingData<Ingredient>>{
        return remote.getAllIngredients()
    }

    suspend fun getSelectedIngredientsByName(ingredientNames: List<String>): List<Ingredient>{
        return local.getSelectedIngredientsByName(ingredientNames = ingredientNames)
    }

    fun searchIngredients(query: String): Flow<PagingData<Ingredient>>{
        return remote.searchIngredients(query = query)
    }

    suspend fun getSelectedLocalDrink(drinkId: Int): Drink{
        return local.getSelectedLocalDrink(drinkId = drinkId)
    }

    fun getAllIngredientFamilies(): Flow<PagingData<IngredientFamily>>{
        return remote.getAllIngredientFamilies()
    }

    suspend fun getSelectedIngredientFamiliesByName(ingredientFamilyNames: List<String>): List<IngredientFamily>{
        return local.getSelectedIngredientFamiliesByName(ingredientFamilyNames = ingredientFamilyNames)
    }

    fun searchIngredientFamilies(query: String): Flow<PagingData<IngredientFamily>>{
        return remote.searchIngredientFamilies(query = query)
    }

    suspend fun getSelectedIngredientFamilyById(ingredientFamilyId: Int): IngredientFamily{
        return local.getSelectedIngredientFamilyById(ingredientFamilyId = ingredientFamilyId)
    }

    fun getDrinksContainingIngredients(query: String): Flow<PagingData<Drink>>{
        return remote.getDrinksContainingIngredients(query = query)
    }

    fun searchIngredientsByIngredientFamilyName(query: String): Flow<PagingData<Ingredient>>{
        return remote.searchIngredientsByIngredientFamilyName(query = query)
    }

}