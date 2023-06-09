package com.example.drinkapp.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.drinkapp.domain.model.Drink
import kotlinx.coroutines.flow.Flow

/** říká knihovně room jak provádět crud operace */

@Dao
interface DrinkDao {

    @Query("SELECT * FROM drink_table ORDER BY id ASC")
    fun getAllRemoteDrinks(): PagingSource<Int, Drink>

    @Query("SELECT * FROM drink_table ORDER BY id ASC")
    fun getAllLocalDrinks(): Flow<List<Drink>>

    @Query("SELECT * FROM drink_table WHERE id=:drinkId")
    fun getSelectedDrink(drinkId: Int): Drink

    @Query(
        """SELECT * FROM drink_table
       WHERE id IN (
           SELECT drinkId FROM drinks_containing_ingredients_table
           WHERE ingredientName IN (:ingredientNames)
           GROUP BY drinkId
           HAVING COUNT(drinkId) = :ingredientNamesCount)
       ORDER BY name ASC"""
    )
    fun getDrinksContainingIngredients(ingredientNames: List<String>, ingredientNamesCount: Int): PagingSource<Int, Drink>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addDrinks(drinks: List<Drink>)

    @Query("DELETE FROM drink_table")
    suspend fun deleteAllDrinks()

}