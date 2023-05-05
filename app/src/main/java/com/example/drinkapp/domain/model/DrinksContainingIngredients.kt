package com.example.drinkapp.domain.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import com.example.drinkapp.util.Constants.DRINKS_CONTAINING_INGREDIENTS_TABLE

@kotlinx.serialization.Serializable
@Entity(
    tableName = DRINKS_CONTAINING_INGREDIENTS_TABLE,
    primaryKeys = ["drinkId", "ingredientName"],
    foreignKeys = [
        ForeignKey(
            entity = Drink::class,
            parentColumns = ["id"],
            childColumns = ["drinkId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Ingredient::class,
            parentColumns = ["name"],
            childColumns = ["ingredientName"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["ingredientName"], unique = true)]
)
data class DrinksContainingIngredients(
    val drinkId: Int,
    val ingredientName: String
)

