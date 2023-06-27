package com.example.drinkapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.drinkapp.util.Constants.INGREDIENT_FAMILY_REMOTE_KEYS_DATABASE_TABLE

@Entity(tableName = INGREDIENT_FAMILY_REMOTE_KEYS_DATABASE_TABLE)
data class IngredientFamilyRemoteKeys (
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val prevPage: Int?,
    val nextPage: Int?,
    val lastUpdated: Long?
)