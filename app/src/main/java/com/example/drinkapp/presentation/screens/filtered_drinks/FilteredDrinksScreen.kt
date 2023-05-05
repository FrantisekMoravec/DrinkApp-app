package com.example.drinkapp.presentation.screens.ingredients

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.example.drinkapp.presentation.common.ListContent
import com.example.drinkapp.presentation.screens.filtered_drinks.FilteredDrinksViewModel
import com.example.drinkapp.ui.theme.statusBarColor
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@ExperimentalCoilApi
@Composable
fun FilteredDrinksScreen(
    navController: NavHostController,
    ingredients: List<String>,
    filteredDrinksViewModel: FilteredDrinksViewModel = hiltViewModel()
){
    val filteredDrinks = filteredDrinksViewModel.filteredDrinks.collectAsLazyPagingItems()

    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(
        color = MaterialTheme.colors.statusBarColor
    )

    Scaffold(
        content = {
            ListContent(
                drinks = filteredDrinks,
                navController = navController
            )
        }
    )
}