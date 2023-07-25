package com.example.drinkapp.presentation.screens.drink_details

import android.util.Log
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.map
import coil.annotation.ExperimentalCoilApi
import com.example.drinkapp.presentation.screens.search_drinks.DrinksSearchViewModel
import com.example.drinkapp.util.Constants.BASE_URL
import com.example.drinkapp.util.PaletteGenerator.convertImageUrlToBitmap
import com.example.drinkapp.util.PaletteGenerator.extractColorFromBitmap
import kotlinx.coroutines.flow.collectLatest

@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
fun DrinkDetailsScreen(
    navController: NavHostController,
    drinkDetailsViewModel: DrinkDetailsViewModel = hiltViewModel(),
    drinksSearchViewModel: DrinksSearchViewModel = hiltViewModel()
) {

    val selectedDrink by drinkDetailsViewModel.selectedDrink.collectAsState()
    val selectedDrink2 = drinksSearchViewModel.searchedDrinks.collectAsLazyPagingItems()
    val colorPalette by drinkDetailsViewModel.colorPalette

    if (selectedDrink == null) {
        drinkDetailsViewModel.drinkId = drinksSearchViewModel.drinkId
    }
    Log.d("safe drink", "DrinkDetailsScreen - selected drink: ${selectedDrink?.name} id: ${selectedDrink?.name}")
    Log.d("safe drink", "DrinkDetailsScreen - selected drink object: $selectedDrink")

    if (colorPalette.isNotEmpty()){
        DrinkDetailsContent(
            navController = navController,
            selectedDrink = selectedDrink,
            colors = colorPalette
        )
        /*
        if (selectedDrink != null){
            DrinkDetailsContent(
                navController = navController,
                selectedDrink = selectedDrink,
                colors = colorPalette
            )
        }else{
            DrinkDetailsContent(
                navController = navController,
                selectedDrink = selectedDrink2.coll,
                colors = colorPalette
            )
        }
         */
    }else{
        drinkDetailsViewModel.generateColorPalette()
    }

    val context = LocalContext.current

    /** obrázek drinku předáme metodě PaletteGenerator a ta si z něj vytvoří vzorník podle kterého vybere jako barvu použít */
    LaunchedEffect(key1 = true){
        drinkDetailsViewModel.uiEvent.collectLatest { event ->
            when(event){
                is UiEvent.GenerateColorPalette -> {
                    val bitmap = convertImageUrlToBitmap(
                        imageUrl = "$BASE_URL${selectedDrink?.image}",
                        context = context
                    )
                    if(bitmap != null){
                        drinkDetailsViewModel.setColorPalette(
                            colors = extractColorFromBitmap(bitmap)
                        )
                    }
                }
            }
        }
    }
}