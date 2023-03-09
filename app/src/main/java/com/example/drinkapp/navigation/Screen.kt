package com.example.drinkapp.navigation

/** přiřazuje k metodám cestu a umožňuje jim předávat parametry */

sealed class Screen(val route: String) {
    object Splash : Screen("splash_screen")
    //object Welcome : Screen("welcome_screen")
    //object Home : Screen("home_screen")
    object DrinkDetails : Screen("drink_details_screen/{drinkId}") {
        fun passDrinkId(drinkId: Int): String {
            return "drink_details_screen/$drinkId"
        }
    }
    object DrinkSearch : Screen("drink_search_screen")

    object IngredientSearch : Screen("ingredient_search_screen")

    //object Main : Screen("main_screen")

    object IngredientDetails: Screen("ingredient_details_screen"){
        fun passIngredientId(ingredientId: Int): String{
            return "ingredient_details_screen/$ingredientId"
        }
    }
}
