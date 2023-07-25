package com.example.drinkapp.domain.model.relations

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.Relation
import com.example.drinkapp.domain.model.Drink
import com.example.drinkapp.domain.model.Ingredient

data class DrinkWithIngredients (
    @Embedded val drink: Drink,
    @Relation(
        parentColumn = "drinkId",
        entityColumn = "ingredientId",
        associateBy = Junction(DrinkIngredientCrossRef::class)
    )
    val ingredients: List<Ingredient>
)