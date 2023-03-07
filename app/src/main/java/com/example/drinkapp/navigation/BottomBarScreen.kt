package com.example.drinkapp.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen (
    val route: String,
    val icon: ImageVector
){
    //TODO změnit ikonu na ikonu drinku
    object Drinks : BottomBarScreen(
        route = "DRINKS",
        icon = Icons.Default.Home
    )
/*
    object Ingredients : BottomBar(
        route = "INGREDIENTS",
        icon = Icons.Default.Ingredients
    )
*/
    object Assistant : BottomBarScreen(
        route = "ASSISTANT",
        icon = Icons.Default.Person
    )

    object Settings : BottomBarScreen(
        route = "SETTINGS",
        icon = Icons.Default.Settings
    )
}