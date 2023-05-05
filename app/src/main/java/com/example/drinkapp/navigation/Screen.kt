package com.example.drinkapp.navigation

/** přiřazuje k metodám cestu a umožňuje jim předávat parametry */

sealed class Screen(val route: String) {
    object Splash : Screen("splash_screen")

    object DrinkDetails : Screen("drink_details_screen/{drinkId}") {
        fun passDrinkId(drinkId: Int): String {
            return "drink_details_screen/$drinkId"
        }
    }
    object DrinkSearch : Screen("drink_search_screen")

    object IngredientSearch : Screen("ingredient_search_screen")

    object FilteredDrinks : Screen("filtered_drinks_screen?ingredients={ingredients}") {
        fun passIngredients(ingredients: List<String>): String {
            val ingredientsArg = ingredients.joinToString(",")
            return "filtered_drinks_screen?ingredients=$ingredientsArg"
        }
    }
}

/*
    object FilteredDrinks : Screen("filtered_drinks_screen/{ingredients}") {
        fun passIngredients(ingredients: List<String>): String {
            val ingredientsString = ingredients.joinToString(separator = ",")
            return "filtered_drinks_screen/$ingredientsString"
        }
    }
*/

//object FilteredDrinks: Screen("filtered_drinks_screen")
