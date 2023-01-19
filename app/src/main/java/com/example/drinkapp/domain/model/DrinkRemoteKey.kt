package com.example.drinkapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.drinkapp.util.Constants.DRINK_REMOTE_KEY_DATABASE_TABLE

@Entity(tableName = DRINK_REMOTE_KEY_DATABASE_TABLE)
data class DrinkRemoteKey(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val prevPage: Int?,
    val nextPage: Int?
)
