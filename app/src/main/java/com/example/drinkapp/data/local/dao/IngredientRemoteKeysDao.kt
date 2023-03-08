package com.example.drinkapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.drinkapp.domain.model.DrinkRemoteKeys
import com.example.drinkapp.domain.model.IngredientRemoteKeys

@Dao
interface IngredientRemoteKeysDao {

    @Query("SELECT * FROM ingredient_remote_keys_table WHERE id = :ingredientId")
    suspend fun getIngredientRemoteKeys(ingredientId: Int): IngredientRemoteKeys?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllIngredientRemoteKeys(ingredientRemoteKeys: List<IngredientRemoteKeys>)

    @Query("DELETE FROM ingredient_remote_keys_table")
    suspend fun deleteAllIngredientRemoteKeys()

}