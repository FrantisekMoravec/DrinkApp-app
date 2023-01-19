package com.example.drinkapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.drinkapp.domain.model.DrinkRemoteKey

@Dao
interface DrinkRemoteKeyDao {

    @Query("SELECT * FROM drink_remote_key_table WHERE id = :id")
    suspend fun getRemoteKey(id: Int): DrinkRemoteKey?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(drinkRemoteKeys: List<DrinkRemoteKey>)

    @Query("DELETE FROM drink_remote_key_table")
    suspend fun deleteAllRemoteKeys()

}