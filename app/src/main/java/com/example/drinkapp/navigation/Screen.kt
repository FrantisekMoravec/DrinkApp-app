package com.example.drinkapp.navigation

/** přiřazuje k metodám cestu a umožňuje jim předávat parametry */

sealed class Screen(val route: String) {
    object Splash : Screen("splash_screen")

    object DrinkDetails : Screen("drink_details/{drinkId}") {
        fun passDrinkId(drinkId: Int): String {
            return "drink_details/$drinkId"
        }
    }
    object DrinkSearch : Screen("drink_search")

    object IngredientSearch : Screen("ingredient_search")

    object FilteredDrinks : Screen("filtered_drinks?ingredients={ingredients}") {
        fun passIngredients(ingredients: List<String>): String {
            val ingredientsArg = ingredients.joinToString(",")
            return "filtered_drinks?ingredients=$ingredientsArg"
        }
    }

    object AboutApp : Screen("about_app")

    object AddDrink : Screen("add_drink")

    object IngredientDetails : Screen("ingredient_details/{ingredientFamilyId}"){
        fun passIngredientId(ingredientFamilyId: Int): String {
            return "ingredient_details/$ingredientFamilyId"
        }
    }
}