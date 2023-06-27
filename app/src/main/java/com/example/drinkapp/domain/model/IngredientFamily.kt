package com.example.drinkapp.domain.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.drinkapp.util.Constants.INGREDIENT_FAMILY_DATABASE_TABLE

@kotlinx.serialization.Serializable
@Entity(tableName = INGREDIENT_FAMILY_DATABASE_TABLE, indices = [Index(value = ["name"], unique = true)])
data class IngredientFamily (
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val description: String,
    val image: String
)