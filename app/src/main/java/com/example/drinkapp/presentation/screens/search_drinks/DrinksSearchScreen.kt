package com.example.drinkapp.presentation.screens.search_drinks

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.example.drinkapp.presentation.common.ListContent

/** tato metoda říká jak bude vypadat fragment pro vyhladávání drinků */

@ExperimentalCoilApi
@Composable
fun DrinksSearchScreen(
    navController: NavHostController,
    drinksSearchViewModel: DrinksSearchViewModel = hiltViewModel()
) {
    val searchQuery by drinksSearchViewModel.searchQuery
    val drinks = drinksSearchViewModel.searchedDrinks.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            DrinksSearchTopBar(
                text = searchQuery,
                onTextChange = {
                   drinksSearchViewModel.drinksUpdateSearchQuery(query = it)
                },
                onSearchClicked = {
                                  drinksSearchViewModel.searchDrinks(query = it)
                },
                onCloseClicked = {
                    navController.popBackStack()
                }
            )
        },
        content = {
            ListContent(drinks = drinks, navController = navController)
        }
    )
}