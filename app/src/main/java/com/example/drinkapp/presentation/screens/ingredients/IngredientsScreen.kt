package com.example.drinkapp.presentation.screens.ingredients

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.example.drinkapp.navigation.Screen
import com.example.drinkapp.presentation.common.ListIngredients
import com.example.drinkapp.presentation.screens.filtered_drinks.FilteredDrinksViewModel

/** tato metoda říká jak má vypadat fragment s ingrediencemi */

@ExperimentalCoilApi
@Composable
fun IngredientsScreen(
    navController: NavHostController,
    ingredientsViewModel: IngredientsViewModel = hiltViewModel(),
    filteredDrinksViewModel: FilteredDrinksViewModel = hiltViewModel()
) {

    val allIngredients = ingredientsViewModel.getAllIngredients.collectAsLazyPagingItems()

    val filteredDrinks = filteredDrinksViewModel.filteredDrinks.collectAsLazyPagingItems()

    // Získání hodnoty z MutableSharedFlow
    val selectedIngredients by filteredDrinksViewModel.selectedIngredients.collectAsState(emptyList())

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
        },
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier
                    .padding(bottom = 50.dp),
                shape = CircleShape,
                onClick = {
                    val selectedIngredientNames = selectedIngredients.map { it.name }

                    Log.d("FAB", "vybrané ingredience: $selectedIngredientNames")
                    Log.d("FAB", "Vyfiltrované drinky: ${filteredDrinks.itemSnapshotList.items.map { "${it.name} (ID: ${it.id})" }}")

                    navController.navigate(Screen.FilteredDrinks.passIngredients(selectedIngredientNames))
                },
            ) {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = "filter by selected ingredients"
                )
            }
        }
    )
}