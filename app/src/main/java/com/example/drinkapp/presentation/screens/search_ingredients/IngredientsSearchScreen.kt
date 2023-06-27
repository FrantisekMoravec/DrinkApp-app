package com.example.drinkapp.presentation.screens.search_ingredients

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.example.drinkapp.presentation.common.ListIngredients
import com.example.drinkapp.presentation.screens.filtered_drinks.FilteredDrinksViewModel
import com.example.drinkapp.ui.theme.statusBarColor
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@ExperimentalCoilApi
@Composable
fun IngredientsSearchScreen(
    navController: NavHostController,
    ingredientsSearchViewModel: IngredientsSearchViewModel = hiltViewModel(),
    filteredDrinksViewModel: FilteredDrinksViewModel = hiltViewModel()
) {
    val searchQuery by ingredientsSearchViewModel.searchQuery
    val ingredients = ingredientsSearchViewModel.searchedIngredientFamilies.collectAsLazyPagingItems()

    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(
        color = MaterialTheme.colors.statusBarColor
    )

    Scaffold(
        topBar = {
            IngredientsSearchTopBar(
                text = searchQuery,
                onTextChange = {ingredientsSearchViewModel.ingredientFamiliesUpdateSearchQuery(query = it)},
                onSearchClicked = {ingredientsSearchViewModel.searchIngredientFamilies(query = it)},
                onCloseClicked = {navController.popBackStack()}
            )
        },
        content = {
            ListIngredients(
                ingredientFamilies = ingredients,
                navController = navController,
                checkedIngredients = filteredDrinksViewModel.checkedIngredientFamilies
            ) { id, name, isChecked ->
                filteredDrinksViewModel.updateCheckedIngredientFamilies(id, name, isChecked)
            }
        }
    )
}