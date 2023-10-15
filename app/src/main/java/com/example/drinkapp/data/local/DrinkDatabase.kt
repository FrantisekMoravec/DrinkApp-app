package com.example.drinkapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.drinkapp.data.local.dao.DrinkDao
import com.example.drinkapp.data.local.dao.DrinkRemoteKeysDao
import com.example.drinkapp.data.local.dao.IngredientDao
import com.example.drinkapp.data.local.dao.IngredientFamilyRemoteKeysDao
import com.example.drinkapp.data.local.dao.IngredientRemoteKeysDao
import com.example.drinkapp.domain.model.Drink
import com.example.drinkapp.domain.model.DrinkRemoteKeys
import com.example.drinkapp.domain.model.DrinksContainingIngredients
import com.example.drinkapp.domain.model.Ingredient
import com.example.drinkapp.domain.model.IngredientFamily
import com.example.drinkapp.domain.model.IngredientFamilyRemoteKeys
import com.example.drinkapp.domain.model.IngredientRemoteKeys

/** databáze uchovávající stažená data */
@Database(
    entities = [
        Ingredient::class,
        IngredientRemoteKeys::class,
        Drink::class,
        DrinkRemoteKeys::class,
        IngredientFamily::class,
        IngredientFamilyRemoteKeys::class,
        DrinksContainingIngredients::class
               ],
    version = 1,
    exportSchema = false
)
@TypeConverters(DatabaseConverter::class)
abstract class DrinkDatabase: RoomDatabase() {

    abstract fun drinkDao(): DrinkDao
    abstract fun drinkRemoteKeysDao(): DrinkRemoteKeysDao
    abstract fun ingredientDao(): IngredientDao
    abstract fun ingredientRemoteKeysDao(): IngredientRemoteKeysDao
    abstract fun ingredientFamilyRemoteKeysDao(): IngredientFamilyRemoteKeysDao
}