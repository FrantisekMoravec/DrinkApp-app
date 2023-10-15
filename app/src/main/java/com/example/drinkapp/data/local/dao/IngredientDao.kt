package com.example.drinkapp.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.drinkapp.domain.model.Ingredient
import com.example.drinkapp.domain.model.IngredientFamily
//TODO sloučit s DrinkDao
/** říká knihovně room jak provádět crud operace */
@Dao
interface IngredientDao {

    @Query("SELECT * FROM ingredient_table ORDER BY id ASC")
    fun getAllIngredients(): PagingSource<Int, Ingredient>

    @Query("SELECT * FROM ingredient_table WHERE id=:ingredientId")
    fun getSelectedIngredientById(ingredientId: Int): Ingredient

    @Query("SELECT * FROM ingredient_table WHERE name=:ingredientNames")
    fun getSelectedIngredientsByName(ingredientNames: List<String>): List<Ingredient>
/*
    @Query("SELECT * FROM ingredient_table WHERE madeByUser=:ingredientMadeByUser")
    fun getIngredientsMadeByUser(ingredientMadeByUser: Boolean): Ingredient
*/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addIngredients(ingredients: List<Ingredient>)

    @Query("DELETE FROM ingredient_table")
    suspend fun deleteAllIngredients()

    @Query("SELECT * FROM ingredient_family_table ORDER BY id ASC")
    fun getAllIngredientFamilies(): PagingSource<Int, IngredientFamily>

    @Query("SELECT * FROM ingredient_family_table WHERE id=:ingredientFamilyId")
    fun getSelectedIngredientFamiliesById(ingredientFamilyId: Int): IngredientFamily

    @Query("SELECT * FROM ingredient_family_table WHERE name=:ingredientFamilyNames")
    fun getSelectedIngredientFamiliesByName(ingredientFamilyNames: List<String>): List<IngredientFamily>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addIngredientFamilies(ingredientFamilies: List<IngredientFamily>)

    @Query("DELETE FROM ingredient_family_table")
    suspend fun deleteAllIngredientFamilies()

/*
    @Query("SELECT * FROM ingredient_table WHERE id=:ingredientIds")
    fun getSelectedIngredients(ingredientIds: List<Ingredient>)
*/


/*
    //TODO tady je možná chyba
    @Query("SELECT * FROM ingredient_table WHERE checked= 'TRUE'")
    suspend fun getCheckedIngredientFamilies(checked: Boolean): Ingredient
*/
/*
    @Query("SELECT * FROM ingredient_table WHERE id=:ingredientId")
    suspend fun checkSelectedIngredient(ingredientId: Int): Ingredient

    @Query("SELECT * FROM ingredient_table WHERE id=:ingredientId")
    suspend fun uncheckSelectedIngredient(ingredientId: Int): Ingredient
 */
}