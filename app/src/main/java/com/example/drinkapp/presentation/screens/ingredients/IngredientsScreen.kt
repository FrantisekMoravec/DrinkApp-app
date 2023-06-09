package com.example.drinkapp.presentation.screens.ingredients

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.example.drinkapp.navigation.Screen
import com.example.drinkapp.presentation.common.ListIngredients
import com.example.drinkapp.presentation.screens.TopBar
import com.example.drinkapp.presentation.screens.filtered_drinks.FilteredDrinksViewModel
import com.example.drinkapp.ui.theme.bottomNavBackgroundColor
import com.example.drinkapp.ui.theme.bottomNavSelectedItemColor
import kotlinx.coroutines.CoroutineScope

/** tato metoda říká jak má vypadat fragment s ingrediencemi */

@ExperimentalCoilApi
@Composable
fun IngredientsScreen(
    navController: NavHostController,
    ingredientsViewModel: IngredientsViewModel = hiltViewModel(),
    filteredDrinksViewModel: FilteredDrinksViewModel = hiltViewModel(),
    scaffoldState: ScaffoldState,
    scope: CoroutineScope
) {
    val mContext = LocalContext.current

    val allIngredients = ingredientsViewModel.getAllIngredients.collectAsLazyPagingItems()

    val filteredDrinks = filteredDrinksViewModel.filteredDrinks.collectAsLazyPagingItems()

    //val selectedIngredients by filteredDrinksViewModel.selectedIngredients.collectAsState(emptyList())

    val allLocalDrinks by filteredDrinksViewModel.allLocalDrinks.collectAsState()

    //val filteredDrinks2 by filteredDrinksViewModel.filteredDrinks.collectAsState()

    //val filteredDrinks = filteredDrinksViewModel.filteredDrinks.collectAsLazyPagingItems()

/*
    LaunchedEffect(filteredDrinksViewModel.filteredDrinks) {
        filteredDrinksViewModel.filteredDrinks.collectAsState(initial = PagingData.empty()).value.map { drink ->
            val drinkName = drink.name
            if (!filteredDrinks2.contains(drinkName)) {
                filteredDrinks2.add(drinkName)
            }
        }
    }
*/
    Scaffold(
        topBar = {
            TopBar (
                text = "hledej ingredience",
                scope = scope,
                scaffoldState = scaffoldState,
                search = true,
                onSearchClicked = {
                    navController.navigate(Screen.IngredientSearch.route)
                }
            )
        },
        content = {
            ListIngredients(
                ingredients = allIngredients,
                navController = navController,
                checkedIngredients = filteredDrinksViewModel.checkedIngredients,
                onCheckedChange = { id, name, isChecked ->
                    if (isChecked) {
                        filteredDrinksViewModel.addCheckedIngredient(id, name)
                    } else {
                        filteredDrinksViewModel.removeCheckedIngredient(id)
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier
                    .padding(bottom = 50.dp),
                shape = CircleShape,
                backgroundColor = MaterialTheme.colors.bottomNavBackgroundColor,
                onClick = {
                    filteredDrinksViewModel.updateFilteredDrinks()

                    //val selectedIngredientNames = selectedIngredients.map { it.name }
                    val allLocalDrinkNames = allLocalDrinks.map { it.name }

                    val filteredDrinks2 = filteredDrinks.itemSnapshotList.items.map { "${it.name} (Id: ${it.id})" }

                    //val allFilteredDrinks = filteredDrinks2.map { it.name }
                    //val allFilteredDrinks = filteredDrinksViewModel.filteredDrinks.value

                    Log.d("FAB", "všechny lokální drinky: $allLocalDrinkNames")
                    Log.d("FAB", "zaškrtnuté ingredience: ${filteredDrinksViewModel.checkedIngredients.value}")
                    //Log.d("FAB", "vybrané ingredience: $selectedIngredientNames")//nefunguje
                    Log.d("FAB", "Vyfiltrované drinky: $filteredDrinks2")
                    Log.d("FAB", "Vyfiltrované drinky - State: ${filteredDrinks.loadState.refresh}")
                    Log.d("FAB", "Vyfiltrované drinky - Item count: ${filteredDrinks.itemCount}")

                    if(false)
                        navController.navigate(Screen.FilteredDrinks.route)
                    else
                        Toast.makeText(mContext, "Funkce filtrování drinků podle zaškrtnutých ingrediencí zatím není dostupná", Toast.LENGTH_LONG).show()
                },
            ) {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = "filter by selected ingredients",
                    tint = MaterialTheme.colors.bottomNavSelectedItemColor
                )
            }
        }
    )
}