package com.example.drinkapp.presentation.screens.search_ingredients

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.example.drinkapp.presentation.common.ListIngredients

@ExperimentalCoilApi
@Composable
fun IngredientsSearchScreen(
    navController: NavHostController,
    ingredientsSearchViewModel: IngredientsSearchViewModel = hiltViewModel()
) {
    val searchQuery by ingredientsSearchViewModel.searchQuery
    val ingredients = ingredientsSearchViewModel.searchedIngredients.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            IngredientsSearchTopBar(
                text = searchQuery,
                onTextChange = {ingredientsSearchViewModel.ingredientsUpdateSearchQuery(query = it)},
                onSearchClicked = {ingredientsSearchViewModel.searchIngredients(query = it)},
                onCloseClicked = {navController.popBackStack()}
            )
        },
        content = {
            ListIngredients(ingredients = ingredients, navController = navController)
        }
    )
}