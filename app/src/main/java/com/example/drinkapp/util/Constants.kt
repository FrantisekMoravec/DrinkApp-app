package com.example.drinkapp.util

object Constants {

    const val APP_VERSION = "0.1"

    const val BASE_URL = "http://10.0.2.2:8080"
    //const val BASE_URL = "https://drinkapp-server.herokuapp.com/"

    const val DRINK_DETAILS_ARGUMENT_KEY = "drinkId"
    const val INGREDIENT_FAMILY_DETAILS_ARGUMENT_KEY = "ingredientFamilyId"
    const val FILTERED_DRINKS_ARGUMENT_KEY = "ingredientFamilyName"
    const val INGREDIENT_DETAILS_ARGUMENT_KEY = "ingredientId"

    const val INGREDIENT_DATABASE_TABLE = "ingredient_table"
    const val INGREDIENT_REMOTE_KEYS_DATABASE_TABLE = "ingredient_remote_keys_table"

    const val INGREDIENT_FAMILY_DATABASE_TABLE = "ingredient_family_table"
    const val INGREDIENT_FAMILY_REMOTE_KEYS_DATABASE_TABLE = "ingredient_family_remote_keys_table"

    const val DRINK_DATABASE_TABLE = "drink_table"
    const val DRINK_REMOTE_KEYS_DATABASE_TABLE = "drink_remote_keys_table"

    const val DRINKS_CONTAINING_INGREDIENTS_TABLE = "drinks_containing_ingredients_table"

    const val DRINK_DATABASE = "drink_database"

    const val DRINK_DESCRIPTION_MAX_LINES = 7

    const val MIN_BACKGROUND_IMAGE_HEIGHT = 0.4f

    const val DRINK_ITEMS_PER_PAGE = 3
    const val INGREDIENT_ITEMS_PER_PAGE = 3
    const val INGREDIENT_FAMILY_ITEMS_PER_PAGE = 3

    const val PREFERENCE_NAME = "drinks_made_by_user_preferences"
}