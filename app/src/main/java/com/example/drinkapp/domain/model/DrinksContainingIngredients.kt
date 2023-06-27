package com.example.drinkapp.domain.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import com.example.drinkapp.util.Constants.DRINKS_CONTAINING_INGREDIENTS_TABLE

@kotlinx.serialization.Serializable
@Entity(
    tableName = DRINKS_CONTAINING_INGREDIENTS_TABLE,
    primaryKeys = ["drinkId", "ingredientFamilyName"],
    foreignKeys = [
        ForeignKey(
            entity = Drink::class,
            parentColumns = ["id"],
            childColumns = ["drinkId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = IngredientFamily::class,
            parentColumns = ["name"],
            childColumns = ["ingredientFamilyName"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["ingredientFamilyName"], unique = true)]
)
data class DrinksContainingIngredients(
    val drinkId: Int,
    val ingredientFamilyName: String
)