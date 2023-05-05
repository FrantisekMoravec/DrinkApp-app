package com.example.drinkapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.drinkapp.util.Constants.DRINK_IMAGE_TABLE

@Entity(tableName = DRINK_IMAGE_TABLE)
data class DrinkImage(
    @PrimaryKey(autoGenerate = false)
    val drinkId: Int,
    val localImagePath: String
)
