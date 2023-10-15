package com.example.drinkapp.domain.model.relations

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.Relation
import com.example.drinkapp.domain.model.Drink
import com.example.drinkapp.domain.model.Ingredient
/*
@Entity(tableName = "ingredient_with_drinks")
data class IngredientWithDrinks(
    @Embedded val ingredient: Ingredient,
    @Relation(
        parentColumn = "id",
        entityColumn = "drinkId",
        associateBy = Junction(DrinkIngredientCrossRef::class)
    )
    val drinks: List<Drink>
)
 */