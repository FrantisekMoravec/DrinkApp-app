package com.example.drinkapp.presentation.screens.ingredients

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.map
import coil.annotation.ExperimentalCoilApi
import com.example.drinkapp.navigation.Screen
import com.example.drinkapp.presentation.common.ListFilteredDrinks
import com.example.drinkapp.presentation.screens.TopBar
import com.example.drinkapp.presentation.screens.filtered_drinks.FilteredDrinksViewModel
import com.example.drinkapp.ui.theme.statusBarColor
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.CoroutineScope

@ExperimentalCoilApi
@Composable
fun FilteredDrinksScreen(
    navController: NavHostController,
    //drinks: List<String>,
    filteredDrinksViewModel: FilteredDrinksViewModel = hiltViewModel(),
    scaffoldState: ScaffoldState,
    scope: CoroutineScope
) {
    val filteredDrinks = filteredDrinksViewModel.filteredDrinks.collectAsLazyPagingItems()

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
            ListFilteredDrinks(
                filteredDrinks = filteredDrinks,
                navController = navController
            )
        }
    )
}
