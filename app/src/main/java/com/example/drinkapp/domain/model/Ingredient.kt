package com.example.drinkapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.drinkapp.util.Constants.INGREDIENT_DATABASE_TABLE

@kotlinx.serialization.Serializable
@Entity(tableName = INGREDIENT_DATABASE_TABLE)
data class Ingredient (
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val image: String,
    val description: String
)