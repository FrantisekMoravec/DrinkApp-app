package com.example.drinkapp.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash_screen")
    object Welcome : Screen("welcome_screen")
    object Home : Screen("home_screen")
    object Details : Screen("details_screen/{drinkId}") {
        fun passDrinkId(drinkId: Int): String {
            return "details_screen/$drinkId"
        }
    }

    object Search : Screen("search_screen")
}
