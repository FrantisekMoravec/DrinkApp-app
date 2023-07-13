package com.example.drinkapp.presentation.screens.ingredient_details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.drinkapp.domain.model.Drink
import com.example.drinkapp.domain.model.IngredientFamily
import com.example.drinkapp.presentation.components.ListDrinks
import com.example.drinkapp.ui.theme.IngredientDetailsScreenBackgroundColor
import com.example.drinkapp.ui.theme.LARGE_PADDING
import com.example.drinkapp.ui.theme.MEDIUM_PADDING
import com.example.drinkapp.util.Constants

@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
fun IngredientDetailsScreen (
    navController: NavHostController,
    ingredientDetailsViewModel: IngredientDetailsViewModel = hiltViewModel()
) {
    val selectedIngredientFamily by ingredientDetailsViewModel.selectedIngredientFamily.collectAsState()
    //val drinksContainingIngredinets by ingredientDetailsViewModel.drinksContainingIngredients.collectAsState()
    val drinks = ingredientDetailsViewModel.drinksContainingIngredients.collectAsLazyPagingItems()

    val context = LocalContext.current

    selectedIngredientFamily?.let {
        IngredientDetailsContent(
        selectedIngredientFamily = it,
        navController = navController,
        drinks = drinks
    )
    }

}


@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
fun IngredientDetailsContent (
    selectedIngredientFamily: IngredientFamily,
    navController: NavHostController,
    drinks: LazyPagingItems<Drink>
) {
    //val selectedIngredientFamily by ingredientDetailsViewModel.selectedIngredientFamily.collectAsState()
    //val drinksContainingIngredients = ingredientDetailsViewModel..collectAsLazyPagingItems()

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
                modifier = Modifier
                    .weight(8f),
                text = selectedIngredientFamily.name,
                color = MaterialTheme.colors.IngredientDetailsScreenBackgroundColor,
                fontSize = MaterialTheme.typography.h4.fontSize,
                fontWeight = FontWeight.Bold
            )
            Image(
                painter = rememberImagePainter(selectedIngredientFamily.image),
                contentDescription = "ingredient family image",
                contentScale = ContentScale.Crop
            )
        }
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "O ingredienci",
            color = MaterialTheme.colors.IngredientDetailsScreenBackgroundColor,
            fontSize = MaterialTheme.typography.subtitle1.fontSize,
            fontWeight = FontWeight.Bold
        )
        Text(
            modifier = Modifier
                .alpha(ContentAlpha.medium)
                .padding(bottom = MEDIUM_PADDING),
            text = selectedIngredientFamily.description,
            color = MaterialTheme.colors.IngredientDetailsScreenBackgroundColor,
            fontSize = MaterialTheme.typography.body1.fontSize,
            maxLines = Constants.DRINK_DESCRIPTION_MAX_LINES
        )

        ListDrinks(
            drinks = drinks,
            navController = navController
        )

    }
}