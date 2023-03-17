package com.example.drinkapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.drinkapp.util.Constants.DRINK_DATABASE_TABLE

/** entita drinku vytvořeného uživatelem */
@Entity(tableName = DRINK_DATABASE_TABLE)
data class DrinkMadeByUser (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val image: Int?, //TODO možná bude potřeba změnit datový typ
    val description: String,
    val ingredients: String,
    val tutorial: String
)