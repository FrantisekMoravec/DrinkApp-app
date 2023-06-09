package com.example.drinkapp.navigation

import com.example.drinkapp.R

sealed class NavDrawerItem(
    val route: String,
    val icon: Int,
    val title: String,
    val isWorking: Boolean
){
    //TODO přidat error report, moje drinky
    object Drinks : NavDrawerItem(
        BottomBarScreen.Drinks.route,
        R.drawable.ic_home,
        "Všechny Drinky",
        true
    )

    object Ingredients : NavDrawerItem(
        BottomBarScreen.Ingredients.route,
        R.drawable.ic_search,
        "Výběr drinků podle ingrediencí",
        true
    )

    object AddDrink : NavDrawerItem(
        Screen.AddDrink.route,
        R.drawable.ic_add,
        "Přidat vlastní drink",
        false
    )

    object AboutApp : NavDrawerItem(
        Screen.AboutApp.route,
        R.drawable.ic_info,
        "O aplikaci",
        true
    )
}