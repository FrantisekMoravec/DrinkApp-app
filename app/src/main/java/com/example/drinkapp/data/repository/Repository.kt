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
/*
    fun getSelectedRemoteDrink(drinkId: Int): Drink{
        return remote.getSelectedRemoteDrink(drinkId = drinkId)
    }
*/
    suspend fun getSelectedLocalDrink(drinkId: Int): Drink{
        return local.getSelectedLocalDrink(drinkId = drinkId)
    }
/*
    suspend fun getAllIngredientsMadeByUser(madeByUser: Boolean): Ingredient {
        return local.getAllIngredientsMadeByUser(madeByUser = madeByUser)
    }
*/
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

    fun getDrinksContainingIngredients(ingredientNames: List<String>): Flow<PagingData<Drink>>{
        return remote.getDrinksContainingIngredients(ingredientNames = ingredientNames)
    }

    fun searchIngredientsByIngredientFamilyName(ingredientFamilyName: String): Flow<PagingData<Ingredient>>{
        return remote.searchIngredientsByIngredientFamilyName(ingredientFamilyName = ingredientFamilyName)
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