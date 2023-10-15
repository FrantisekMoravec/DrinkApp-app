package com.example.drinkapp.presentation.screens.ingredient_details

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ContentAlpha
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.drinkapp.R
import com.example.drinkapp.domain.model.Drink
import com.example.drinkapp.domain.model.Ingredient
import com.example.drinkapp.domain.model.IngredientFamily
import com.example.drinkapp.presentation.components.ListDrinks
import com.example.drinkapp.ui.theme.IngredientDetailsScreenBackgroundColor
import com.example.drinkapp.ui.theme.IngredientDetailsScreenTextColor
import com.example.drinkapp.ui.theme.LARGE_PADDING
import com.example.drinkapp.ui.theme.MEDIUM_PADDING
import com.example.drinkapp.ui.theme.statusBarColor
import com.example.drinkapp.util.Constants
import com.example.drinkapp.util.Constants.BASE_URL
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
fun IngredientDetailsScreen (
    navController: NavHostController,
    ingredientDetailsViewModel: IngredientDetailsViewModel = hiltViewModel()
) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(
        color = MaterialTheme.colors.statusBarColor
    )

    val selectedIngredientFamily by ingredientDetailsViewModel.selectedIngredientFamily.collectAsState()
    val drinks = ingredientDetailsViewModel.drinksContainingIngredients.collectAsLazyPagingItems()
    val ingredients = ingredientDetailsViewModel.ingredientsOfIngredientFamily.collectAsLazyPagingItems()

    selectedIngredientFamily?.let {
        IngredientDetailsContent(
        selectedIngredientFamily = it,
        navController = navController,
        drinks = drinks,
        ingredients = ingredients
    )
    }
}

@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
fun IngredientDetailsContent (
    selectedIngredientFamily: IngredientFamily,
    navController: NavHostController,
    drinks: LazyPagingItems<Drink>,
    ingredients: LazyPagingItems<Ingredient>
) {

    val painter = rememberImagePainter(data = "${BASE_URL}${selectedIngredientFamily.image}"){
        placeholder(R.drawable.ic_placeholder)
        error(R.drawable.ic_placeholder)
    }

    Column(modifier = Modifier
        .background(MaterialTheme.colors.IngredientDetailsScreenBackgroundColor)
        .padding(MEDIUM_PADDING)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = LARGE_PADDING),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = selectedIngredientFamily.name,
                color = MaterialTheme.colors.IngredientDetailsScreenTextColor,
                fontSize = MaterialTheme.typography.h4.fontSize,
                fontWeight = FontWeight.Bold,
                overflow = TextOverflow.Ellipsis
            )
            Image(
                modifier = Modifier
                    .fillMaxWidth(),
                painter = painter,
                contentDescription = "ingredient family image"
                //,
                //contentScale = ContentScale.Crop
            )
        }
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "O ingredienci",
            color = MaterialTheme.colors.IngredientDetailsScreenTextColor,
            fontSize = MaterialTheme.typography.subtitle1.fontSize,
            fontWeight = FontWeight.Bold
        )
        Text(
            modifier = Modifier
                .alpha(ContentAlpha.medium)
                .padding(bottom = MEDIUM_PADDING),
            text = selectedIngredientFamily.description,
            color = MaterialTheme.colors.IngredientDetailsScreenTextColor,
            fontSize = MaterialTheme.typography.body1.fontSize,
            maxLines = Constants.DRINK_DESCRIPTION_MAX_LINES
        )

        val ingredientsLog = ingredients.itemSnapshotList.items.map { "${it.name} (Id: ${it.id})" }
        val drinksLog = drinks.itemSnapshotList.items.map { "${it.name} (Id: ${it.id})" }
        Log.d("ingredient","ingredience(IngredientDetailsScreen): $ingredientsLog")
        Log.d("ingredient","drinky: $drinksLog")

        ListDrinks(
            drinks = drinks,
            navController = navController
        )
    }
}