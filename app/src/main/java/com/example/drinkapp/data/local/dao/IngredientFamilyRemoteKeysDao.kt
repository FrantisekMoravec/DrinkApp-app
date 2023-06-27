package com.example.drinkapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.drinkapp.domain.model.IngredientFamilyRemoteKeys

@Dao
interface IngredientFamilyRemoteKeysDao {

    @Query("SELECT * FROM ingredient_family_remote_keys_table WHERE id = :ingredientFamilyId")
    suspend fun getIngredientFamilyRemoteKeys(ingredientFamilyId: Int): IngredientFamilyRemoteKeys?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllIngredientFamilyRemoteKeys(ingredientFamilyRemoteKeys: List<IngredientFamilyRemoteKeys>)

    @Query("DELETE FROM ingredient_family_remote_keys_table")
    suspend fun deleteAllIngredientFamilyRemoteKeys()

}