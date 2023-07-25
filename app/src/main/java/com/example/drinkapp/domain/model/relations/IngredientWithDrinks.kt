package com.example.drinkapp.domain.model.relations

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.Relation
import com.example.drinkapp.domain.model.Drink
import com.example.drinkapp.domain.model.Ingredient

data class IngredientWithDrinks(
    @Embedded val ingredient: Ingredient,
    @Relation(
        parentColumn = "ingredientId",
        entityColumn = "drinkId",
        associateBy = Junction(DrinkIngredientCrossRef::class)
    )
    val drinks: List<Drink>
)