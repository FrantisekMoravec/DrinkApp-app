package com.example.drinkapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.drinkapp.util.Constants.DRINK_DATABASE_TABLE

/** entita drinku */

@kotlinx.serialization.Serializable
@Entity(tableName = DRINK_DATABASE_TABLE)
data class Drink(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val image: String,
    val description: String,
    val ingredients: List<String>,
    val tutorial: String,
    val madeByUser: Boolean
)
