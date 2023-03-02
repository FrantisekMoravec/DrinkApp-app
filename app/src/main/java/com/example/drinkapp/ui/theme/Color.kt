package com.example.drinkapp.ui.theme

import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

/** tento soubor je seznam použitých barev knihovnou compose */

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)
val LightBlue = Color(0xFF44A9FF)
val DarkBlue = Color(0xFF003561)
val StarColor = Color(0xFFFFC940)
val LightGray = Color(0xFFD8D8D8)

val ShimmerLightGray = Color(0xFFF1F1F1)
val ShimmerMediumGray = Color(0xFFE3E3E3)
val ShimmerDarkGray = Color(0xFF1D1D1D)

/** barva je definována podle toho zda je zařízení v tmavém či světlém módu */
val Colors.topAppBarContentColor: Color
    @Composable
    get() = if (isLight) Color.White else Color.LightGray

val Colors.topAppBarBackgroundColor: Color
    @Composable
    get() = if (isLight) DarkBlue else Color.Black