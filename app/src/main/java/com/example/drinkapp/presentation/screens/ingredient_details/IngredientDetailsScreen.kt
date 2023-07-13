package com.example.drinkapp.presentation.screens.ingredient_details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.drinkapp.R
import com.example.drinkapp.domain.model.IngredientFamily
import com.example.drinkapp.ui.theme.IngredientDetailsScreenBackgroundColor
import com.example.drinkapp.ui.theme.IngredientDetailsScreenTextColor
import com.example.drinkapp.ui.theme.LARGE_PADDING
import com.example.drinkapp.ui.theme.MEDIUM_PADDING
import com.example.drinkapp.util.Constants
import com.example.drinkapp.util.Constants.BASE_URL

@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
fun IngredientDetailsScreen (
    navController: NavHostController,
    ingredientDetailsViewModel: IngredientDetailsViewModel = hiltViewModel()
) {

    val selectedIngredientFamily by ingredientDetailsViewModel.selectedIngredientFamily.collectAsState()
    //val drinks = ingredientDetailsViewModel.drinksContainingIngredients.collectAsLazyPagingItems()

    //val context = LocalContext.current

    selectedIngredientFamily?.let {
        IngredientDetailsContent(
        selectedIngredientFamily = it,
        navController = navController
        //,
        //drinks = drinks
    )
    }

/*
    Text(
        modifier = Modifier
            .fillMaxSize(),
        text = "ingredient details screen",
        color = Color.Black,
        fontSize = MaterialTheme.typography.h2.fontSize,
        fontWeight = FontWeight.Bold
    )
*/
}


@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
fun IngredientDetailsContent (
    selectedIngredientFamily: IngredientFamily,
    navController: NavHostController
    //,
    //drinks: LazyPagingItems<Drink>
) {
    //val selectedIngredientFamily by ingredientDetailsViewModel.selectedIngredientFamily.collectAsState()
    //val drinksContainingIngredients = ingredientDetailsViewModel..collectAsLazyPagingItems()

    /*
        Text(
        modifier = Modifier
            .fillMaxSize(),
        text = "ingredient details screen",
        color = Color.Black,
        fontSize = MaterialTheme.typography.h2.fontSize,
        fontWeight = FontWeight.Bold
    )
     */


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
                modifier = Modifier
                    .weight(8f),
                text = selectedIngredientFamily.name,
                color = MaterialTheme.colors.IngredientDetailsScreenTextColor,
                fontSize = MaterialTheme.typography.h4.fontSize,
                fontWeight = FontWeight.Bold
            )
            Image(
                painter = painter,
                contentDescription = "ingredient family image",
                contentScale = ContentScale.Crop
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
/*
        ListDrinks(
            drinks = drinks,
            navController = navController
        )
 */
    }
}