package com.example.drinkapp.domain.model.relations

import androidx.room.Entity

@Entity(tableName = "drink_ingredient_cross_ref", primaryKeys = ["drinkId", "ingredientId"])
class DrinkIngredientCrossRef(
    val drinkId: Int,
    val ingredientId: Int
)