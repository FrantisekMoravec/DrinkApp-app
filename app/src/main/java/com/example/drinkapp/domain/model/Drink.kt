package com.example.drinkapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.drinkapp.util.Constants.DRINK_DATABASE_TABLE

/** entita drink */

@kotlinx.serialization.Serializable
@Entity(tableName = DRINK_DATABASE_TABLE)
data class Drink(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val image: String,
    val description: String,
    val rating: Double,
    val ingredients: List<String>,
    val tutorial: String
)
