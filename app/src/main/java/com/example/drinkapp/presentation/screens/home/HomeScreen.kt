package com.example.drinkapp.presentation.screens.home

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.example.drinkapp.presentation.common.ListContent

@ExperimentalCoilApi
@Composable
fun HomeScreen(
    navController: NavHostController,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val allDrinks = homeViewModel.getAllDrinks.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            HomeTopBar (onSearchClicked = {})
        },
        content = {
            ListContent(
                drinks = allDrinks,
                navController = navController
            )
        }
    )
}