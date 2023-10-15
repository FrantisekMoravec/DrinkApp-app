package com.example.drinkapp.presentation.screens.drinks

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.example.drinkapp.navigation.Screen
import com.example.drinkapp.presentation.common.ListContent
import com.example.drinkapp.presentation.screens.TopBar
import com.example.drinkapp.ui.theme.statusBarColor
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.CoroutineScope

@ExperimentalCoilApi
@Composable
fun DrinksScreen(
    navController: NavHostController,
    drinksViewModel: DrinksViewModel = hiltViewModel(),
    scaffoldState: ScaffoldState,
    scope: CoroutineScope
) {
    val allDrinks = drinksViewModel.getAllDrinks.collectAsLazyPagingItems()

    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(
        color = MaterialTheme.colors.statusBarColor
    )

    Scaffold(
        topBar = {
            TopBar (
                text = "hledej drinky",
                scope = scope,
                scaffoldState = scaffoldState,
                search = true,
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