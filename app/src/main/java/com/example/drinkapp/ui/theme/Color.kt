package com.example.drinkapp.ui.theme

import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.DarkGray

//TODO doladit barvy
/** tento soubor je seznam použitých barev knihovnou compose */

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)
val LightBlue = Color(0xFF44A9FF)
val DarkBlue = Color(0xFF003561)
val StarColor = Color(0xFFFFC940)
val LightGrey = Color(0xFFD8D8D8)
val DarkerBlue = Color(0xFF003B6D)
val DarkestBlue = Color(0xFF001A30)
val DarkGrey = Color(0xFF7A7A7A)

val ShimmerLightGrey = Color(0xFFF1F1F1)
val ShimmerMediumGrey = Color(0xFFE3E3E3)
val ShimmerDarkGrey = Color(0xFF575757)
val ShimmerDarkestGrey = Color(0xFF1D1D1D)

/** barva je definována podle toho zda je zařízení v tmavém či světlém módu */
val Colors.topAppBarContentColor: Color
    @Composable
    get() = if (isLight) Color.White else Color.LightGray

val Colors.topAppBarBackgroundColor: Color
    @Composable
    get() = if (isLight) DarkBlue else Color.Black

val Colors.bottomNavBackgroundColor: Color
    @Composable
    get() = if (isLight) DarkBlue else Color.Black

val Colors.bottomNavUnselectedItemsColor: Color
    @Composable
    get() = if (isLight) DarkestBlue else LightGrey

val Colors.bottomNavSelectedItemColor: Color
    @Composable
    get() = if (isLight) LightBlue else ShimmerMediumGrey

val Colors.titleColor
    @Composable
    get() = if (isLight) DarkGray else LightGrey

val Colors.statusBarColor
    @Composable
    get() = if (isLight) DarkBlue else Color.Black

val Colors.drinksScreenBackgroundColor
    @Composable
    get() = if (isLight) DarkestBlue else Color.Black

val Colors.ingredientNamesColor: Color
    @Composable
    get() = if (isLight) ShimmerDarkestGrey else Color.White

val Colors.ingredientBoxBackgroundColor: Color
    @Composable
    get() = if (isLight) Color.Black else Color.White

//TODO předělat navDrawerSelectedItemColor
val Colors.navDrawerSelectedItemColor: Color
    @Composable
    get() = if (isLight) DarkGrey else DarkGrey

val Colors.aboutAppBackgroundColor: Color
    @Composable
    get() = if (isLight) Color.White else ShimmerMediumGrey

val Colors.aboutAppTextColor: Color
    @Composable
    get() = if (isLight) Color.Black else Color.Black