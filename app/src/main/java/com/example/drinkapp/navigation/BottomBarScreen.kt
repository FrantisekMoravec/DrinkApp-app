package com.example.drinkapp.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen (
    val route: String,
    val icon: ImageVector
){
    object Drinks : BottomBarScreen(
        route = "drinks",
        icon = Icons.Default.Home
    )

    object Ingredients : BottomBarScreen(
        route = "ingredients",
        icon = Icons.Default.Search
    )
    /*
    {
        fun passIngredientId(ingredientId: Int): String{
            return "INGREDIENTS/$ingredientId"
        }
    }
*/
    object Assistant : BottomBarScreen(
        route = "assistant",
        icon = Icons.Default.Person
    )

    object Settings : BottomBarScreen(
        route = "settings",
        icon = Icons.Default.Settings
    )
}