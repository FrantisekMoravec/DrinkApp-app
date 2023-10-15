package com.example.drinkapp.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.drinkapp.domain.model.Drink
import com.example.drinkapp.domain.model.Ingredient
import com.example.drinkapp.domain.model.IngredientFamily
import kotlinx.coroutines.flow.Flow

@Dao
interface DrinkDao {

    @Query("SELECT * FROM drink_table ORDER BY id ASC")
    fun getAllRemoteDrinks(): PagingSource<Int, Drink>

    @Query("SELECT * FROM drink_table ORDER BY id ASC")
    fun getAllLocalDrinks(): Flow<List<Drink>>

    @Query("SELECT * FROM drink_table WHERE id=:drinkId")
    fun getSelectedLocalDrink(drinkId: Int): Drink

    @Query("SELECT * FROM drink_table WHERE name LIKE '%' || :query || '%'")
    fun getSearchedDrinks(query: String): PagingSource<Int, Drink>

    @Query("SELECT * FROM drink_table INNER JOIN ingredient_table ON drink_table.ingredients LIKE '%' || :ingredientNames || '%'")
    fun getDrinksContainingIngredients(ingredientNames: List<String>): PagingSource<Int, Drink>

    @Query("SELECT name FROM ingredient_table WHERE ingredientFamily = :ingredientFamilyName")
    fun getIngredientNamesOfIngredientFamily(ingredientFamilyName: String): Flow<List<String>>

    @Query("SELECT * FROM ingredient_family_table WHERE id = :ingredientFamilyId")
    fun getSelectedIngredientFamilyById(ingredientFamilyId: Int): IngredientFamily

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addDrinks(drinks: List<Drink>)

    @Query("DELETE FROM drink_table")
    suspend fun deleteAllDrinks()
/*
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDrinkIngredientCrossRef(crossRef: DrinkIngredientCrossRef)
 */
}