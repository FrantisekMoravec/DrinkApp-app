package com.example.drinkapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.drinkapp.domain.model.DrinkRemoteKeys

@Dao
interface DrinkRemoteKeysDao {

    @Query("SELECT * FROM drink_remote_keys_table WHERE id = :drinkId")
    suspend fun getDrinkRemoteKeys(drinkId: Int): DrinkRemoteKeys?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllDrinkRemoteKeys(drinkRemoteKeys: List<DrinkRemoteKeys>)

    @Query("DELETE FROM drink_remote_keys_table")
    suspend fun deleteAllDrinkRemoteKeys()

}