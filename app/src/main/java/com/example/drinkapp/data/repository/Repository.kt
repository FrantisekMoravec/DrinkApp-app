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
/*
    fun getSelectedRemoteDrink(drinkId: Int): Drink{
        return remote.getSelectedRemoteDrink(drinkId = drinkId)
    }
*/
    suspend fun getSelectedLocalDrink(drinkId: Int): Drink{
        return local.getSelectedLocalDrink(drinkId = drinkId)
    }

    suspend fun getAllIngredientsMadeByUser(madeByUser: Boolean): Ingredient {
        return local.getAllIngredientsMadeByUser(madeByUser = madeByUser)
    }

    fun getDrinksContainingIngredients(ingredientNames: List<String>, ingredientNamesCount: Int): Flow<PagingData<Drink>> {
        return local.getDrinksContainingIngredients(ingredientNames = ingredientNames, ingredientNamesCount = ingredientNamesCount)
    }
}
/*
    suspend fun getSelectedIngredients(ingredientIds: List<Int>): List<Ingredient>{
        return local.getSelectedIngredients(ingredientIds = ingredientIds)
    }
*/

/*
    suspend fun getSelectedIngredientById(ingredientId: Int): Ingredient{
        return local.getSelectedIngredientById(ingredientId = ingredientId)
    }
*/
/*
    fun getSelectedDrinkMadeByUser(id: Int): Flow<DrinkMadeByUser> {
        return drinkMadeByUserDao.getSelectedDrinkMadeByUser(id = id)
    }

    suspend fun addDrinkMadeByUser(drinkMadeByUser: DrinkMadeByUser){
        drinkMadeByUserDao.addDrinkMadeByUser(drinkMadeByUser = drinkMadeByUser)
    }

    suspend fun updateDrinkMadeByUser(drinkMadeByUser: DrinkMadeByUser){
        drinkMadeByUserDao.updateDrinkMadeByUser(drinkMadeByUser = DrinkMadeByUser)
    }

    suspend fun deleteDrinkMadeByUser(drinkMadeByUser: DrinkMadeByUser){
        drinkMadeByUserDao.deleteDrinkMadeByUser(drinkMadeByUser = drinkMadeByUser)
    }

    fun searchDrinkMadeByUser(query: String): Flow<List<DrinkMadeByUser>>{
        return drinkMadeByUserDao.searchDrinkMadeByUser(searchDrinkMadeByUser = query)
    }
*/