package com.example.drinkapp.navigation

import com.example.drinkapp.R

sealed class NavDrawerItem(
    val route: String,
    val icon: Int,
    val title: String
){
    //TODO přidat error report, moje drinky
    object Drinks : NavDrawerItem(
        "drinks",
        R.drawable.ic_home,
        "Všechny Drinky"
    )

    object Ingredients : NavDrawerItem(
        "ingredients",
        R.drawable.ic_search,
        "Výběr drinků podle ingrediencí"
    )

    object AddDrink : NavDrawerItem(
        "add_drink",
        R.drawable.ic_add,
        "Přidat vlastní drink"
    )

    object AboutApp : NavDrawerItem(
        "about_app",
        R.drawable.ic_info,
        "O aplikaci"
    )
}