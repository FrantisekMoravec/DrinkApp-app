package com.example.drinkapp.presentation.screens.ingredients

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.map
import coil.annotation.ExperimentalCoilApi
import com.example.drinkapp.presentation.common.ListFilteredDrinks
import com.example.drinkapp.presentation.screens.filtered_drinks.FilteredDrinksViewModel
import com.example.drinkapp.ui.theme.statusBarColor
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@ExperimentalCoilApi
@Composable
fun FilteredDrinksScreen(
    navController: NavHostController,
    drinks: List<String>,
    filteredDrinksViewModel: FilteredDrinksViewModel = hiltViewModel()
) {
    val filteredDrinks = filteredDrinksViewModel.filteredDrinks.collectAsLazyPagingItems()

    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(
        color = MaterialTheme.colors.statusBarColor
    )

    Scaffold(
        content = {
            ListFilteredDrinks(
                filteredDrinks = filteredDrinks,
                navController = navController
            )
        }
    )
}
