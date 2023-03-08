package com.example.drinkapp.presentation.screens.ingredients

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.example.drinkapp.navigation.Screen
import com.example.drinkapp.presentation.common.ListContent
import com.example.drinkapp.presentation.screens.drinks.DrinksTopBar

/** tato metoda říká jak má vypadat fragment s ingrediencemi */

@ExperimentalCoilApi
@Composable
fun IngredientsScreen(
    navController: NavHostController,
    ingredientsViewModel: IngredientsViewModel = hiltViewModel()
) {
    /** vybere ingredience z viewmodelu */
    val allIngredients = ingredientsViewModel.getAllIngredients.collectAsLazyPagingItems()

    //TODO doklepat podle drinksScreenu
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "INGREDIENTS",
            fontSize = MaterialTheme.typography.h3.fontSize,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}