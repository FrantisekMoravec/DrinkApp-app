package com.example.drinkapp.util

object Constants {

    const val BASE_URL = "http://10.0.2.2:8080"

    const val DRINK_DETAILS_ARGUMENT_KEY = "drinkId"
    const val INGREDIENT_DETAILS_ARGUMENT_KEY = "ingredientId"

    const val INGREDIENT_DATABASE_TABLE = "ingredient_table"
    const val INGREDIENT_REMOTE_KEYS_DATABASE_TABLE = "ingredient_remote_keys_table"

    const val DRINK_DATABASE_TABLE = "drink_table"
    const val DRINK_REMOTE_KEYS_DATABASE_TABLE = "drink_remote_keys_table"

    const val DRINK_DATABASE = "drink_database"

    const val DRINK_DESCRIPTION_MAX_LINES = 7

    const val MIN_BACKGROUND_IMAGE_HEIGHT = 0.4f

    //TODO změnit velikost stránky
    const val DRINK_ITEMS_PER_PAGE = 3
    const val INGREDIENT_ITEMS_PER_PAGE = 3
}