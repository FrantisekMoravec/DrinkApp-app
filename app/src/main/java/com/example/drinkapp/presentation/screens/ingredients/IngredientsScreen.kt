package com.example.drinkapp.presentation.screens.ingredients

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.example.drinkapp.navigation.Screen
import com.example.drinkapp.presentation.common.ListIngredients

/** tato metoda říká jak má vypadat fragment s ingrediencemi */

@ExperimentalCoilApi
@Composable
fun IngredientsScreen(
    navController: NavHostController,
    ingredientsViewModel: IngredientsViewModel = hiltViewModel()
) {
    /** vybere ingredience z viewmodelu */
    val allIngredients = ingredientsViewModel.getAllIngredients.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            IngredientsTopBar (
                onSearchClicked = {
                    navController.navigate(Screen.IngredientSearch.route)
                }
            )
        },
        content = {
            ListIngredients(
                ingredients = allIngredients,
                navController = navController
            )
        }
    )
}