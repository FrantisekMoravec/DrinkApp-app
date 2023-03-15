package com.example.drinkapp.presentation.screens.details

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi

@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
fun DrinkDetailsScreen(
    navController: NavHostController,
    drinkDetailsViewModel: DrinkDetailsViewModel = hiltViewModel()
    ) {
    val selectedDrink by drinkDetailsViewModel.selectedDrink.collectAsState()

    DrinkDetailsContent(navController = navController, selectedDrink = selectedDrink)
}