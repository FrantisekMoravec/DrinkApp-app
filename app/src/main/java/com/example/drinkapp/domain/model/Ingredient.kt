package com.example.drinkapp.domain.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.drinkapp.util.Constants.INGREDIENT_DATABASE_TABLE

/** entita ingredience */

@kotlinx.serialization.Serializable
@Entity(tableName = INGREDIENT_DATABASE_TABLE, indices = [Index(value = ["name"], unique = true)])
data class Ingredient (
    @PrimaryKey(autoGenerate = false)
    val ingredientId: Int,
    val name: String,
    val ingredientFamily: String
)