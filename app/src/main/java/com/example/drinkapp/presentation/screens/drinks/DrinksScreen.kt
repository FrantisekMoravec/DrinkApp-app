package com.example.drinkapp.presentation.screens.drinks

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.example.drinkapp.navigation.Screen
import com.example.drinkapp.presentation.common.ListContent

/** tato metoda říká jak má vypadat fragment s drinky */

@ExperimentalCoilApi
@Composable
fun DrinksScreen(
    navController: NavHostController,
    drinksViewModel: DrinksViewModel = hiltViewModel()
) {
    /** vybere drinky z viewmodelu */
    val allDrinks = drinksViewModel.getAllDrinks.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            DrinksTopBar (
                onSearchClicked = {
                    navController.navigate(Screen.DrinkSearch.route)
                }
            )
        },
        content = {
            ListContent(
                drinks = allDrinks,
                navController = navController
            )
        }
    )
}