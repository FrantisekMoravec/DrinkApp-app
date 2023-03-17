package com.example.drinkapp.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.drinkapp.domain.model.DrinkMadeByUser
import kotlinx.coroutines.flow.Flow

@Dao
interface DrinkMadeByUserDao {

    @Query("SELECT * FROM drink_table ORDER BY id ASC")
    fun getAllDrinksMadeByUser(): Flow<List<DrinkMadeByUser>>

    @Query("SELECT * FROM drink_table WHERE id=:id")
    fun getSelectedDrinkMadeByUser(id: Int): Flow<DrinkMadeByUser>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addDrinkMadeByUser(drinkMadeByUser: DrinkMadeByUser)

    @Update
    suspend fun updateDrinkMadeByUser(drinkMadeByUser: DrinkMadeByUser)

    @Delete
    suspend fun deleteDrinkMadeByUser(drinkMadeByUser: DrinkMadeByUser)

    @Query("SELECT * FROM drink_table WHERE name LIKE :searchDrinkMadeByUser")
    fun searchDrinkMadeByUser(searchDrinkMadeByUser: String): Flow<List<DrinkMadeByUser>>

}