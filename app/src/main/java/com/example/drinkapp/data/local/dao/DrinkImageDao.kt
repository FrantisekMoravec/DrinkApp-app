package com.example.drinkapp.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.drinkapp.domain.model.DrinkImage

@Dao
interface DrinkImageDao {
    @Query("SELECT * FROM drink_image_table WHERE drinkId = :drinkId")
    suspend fun getDrinkImage(drinkId: Int): DrinkImage?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDrinkImage(drinkImage: DrinkImage)

    @Delete
    suspend fun deleteDrinkImage(drinkImage: DrinkImage)
}