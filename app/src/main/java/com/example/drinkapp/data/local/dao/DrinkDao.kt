package com.example.drinkapp.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.drinkapp.domain.model.Drink
import com.example.drinkapp.domain.model.IngredientFamily
import com.example.drinkapp.domain.model.relations.DrinkIngredientCrossRef
import kotlinx.coroutines.flow.Flow

/** říká knihovně room jak provádět crud operace */

@Dao
interface DrinkDao {

    @Query("SELECT * FROM drink_table ORDER BY drinkId ASC")
    fun getAllRemoteDrinks(): PagingSource<Int, Drink>

    @Query("SELECT * FROM drink_table ORDER BY drinkId ASC")
    fun getAllLocalDrinks(): Flow<List<Drink>>

    @Query("SELECT * FROM drink_table WHERE drinkId=:drinkId")
    fun getSelectedLocalDrink(drinkId: Int): Drink

    @Query("SELECT * FROM drink_table WHERE name LIKE '%' || :query || '%'")
    fun getSearchedDrinks(query: String): PagingSource<Int, Drink>


/*
    @Query("SELECT * FROM drink_table WHERE id=:drinkId")
    fun getSelectedRemoteDrink(drinkId: Int): Flow<Drink>
    */
/*
    @Query(
        """SELECT * FROM drink_table
       WHERE id IN (
           SELECT drinkId FROM drinks_containing_ingredients_table
           WHERE ingredientFamilyName IN (:ingredientNames)
           GROUP BY drinkId)
       ORDER BY name ASC"""
    )
    fun getDrinksContainingIngredients(ingredientNames: List<String>): PagingSource<Int, Drink>
*/

    //@Query("SELECT * FROM drink_table INNER JOIN ingredient_table ON drink_table.ingredients LIKE :ingredientNames")

    @Query("SELECT * FROM drink_table WHERE drinkId IN (SELECT DISTINCT (drinkId) FROM drink_ingredient_cross_ref WHERE ingredientId IN (:ingredientIds))")
    fun getDrinksContainingIngredients(ingredientIds: List<Int>): PagingSource<Int, Drink>

    @Query("SELECT ingredientId FROM ingredient_table WHERE ingredientFamily = :ingredientFamilyName")
    fun getIngredientIdsOfIngredientFamily(ingredientFamilyName: String): List<Int>
/*
    @Query("SELECT * FROM drink_table WHERE drink_table.ingredients LIKE '%' || :ingredientNames || '%'")
    fun getDrinksContainingIngredients(ingredientNames: List<String>): PagingSource<Int, Drink>
*/
    //@Query("SELECT name FROM ingredient_table WHERE ingredientFamily = :ingredientFamilyName")
/*
    @Query("SELECT name FROM ingredient_table WHERE ingredientFamily = :ingredientFamilyName")
    fun getIngredientIdsOfIngredientFamily(ingredientFamilyName: String): List<String>
*/
    @Query("SELECT * FROM ingredient_family_table WHERE id = :ingredientFamilyId")
    fun getSelectedIngredientFamilyById(ingredientFamilyId: Int): IngredientFamily

    @Query("SELECT COUNT(drinkId) FROM drink_table")
    fun getDrinkCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDrinks(drinks: List<Drink>)

    @Query("DELETE FROM drink_table")
    suspend fun deleteAllDrinks()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDrinkIngredientCrossRef(crossRef: DrinkIngredientCrossRef)

}