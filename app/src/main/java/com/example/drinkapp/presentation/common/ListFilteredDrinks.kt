package com.example.drinkapp.presentation.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.drinkapp.R
import com.example.drinkapp.data.local.dao.DrinkImageDao
import com.example.drinkapp.domain.model.Drink
import com.example.drinkapp.navigation.Screen
import com.example.drinkapp.presentation.components.RatingWidget
import com.example.drinkapp.presentation.components.ShimmerEffect
import com.example.drinkapp.ui.theme.*
import com.example.drinkapp.util.Constants

/** tento soubor slouží k zobrazování obsahu */

@ExperimentalCoilApi
@Composable
fun ListFilteredDrinks(
    drinks: LazyPagingItems<Drink>,
    navController: NavHostController,
    selectedIngredients: List<String>
) {
    val result = FilteredDrinksHandlePagingResult(drinks = drinks)

    if (result){
        LazyColumn(
            modifier = Modifier
                .background(MaterialTheme.colors.drinksScreenBackgroundColor),
            contentPadding = PaddingValues(all = SMALL_PADDING),
            verticalArrangement = Arrangement.spacedBy(SMALL_PADDING)
        ){
            items(
                items = drinks,
                key = { drink ->
                    drink.id
                }
            ){ drink ->
                drink?.let {
                    // Filtrujte nápoje podle vybraných ingrediencí
                    if (drink.ingredients.intersect(selectedIngredients).isNotEmpty()) {
                        FilteredDrinkItem(drink = it, navController = navController)
                    }
                }
            }
        }
    }
}

/** tato metoda říká co se má stát podle výsledků načítání */
@Composable
fun FilteredDrinksHandlePagingResult(
    drinks: LazyPagingItems<Drink>
): Boolean {
    drinks.apply {
        /** tato konstanta nám řekne co se stalo za chybu pokud nějaká nastane */
        val error = when{
            loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
            loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
            loadState.append is LoadState.Error -> loadState.append as LoadState.Error
            else -> null
        }

        return when{
            /** pokud se budou data načítat zobrazí se mlhavý efekt */
            loadState.refresh is LoadState.Loading -> {
                ShimmerEffect()
                false
            }
            /** pokud nastane chyba zobrazí se fragment EmptyScreen a na něm příslušná chyba */
            error != null ->{
                EmptyScreen(error = error, drinks = drinks)
                false
            }

            drinks.itemCount < 1 -> {
                EmptyScreen()
                false
            }

            else -> true
        }
    }
}

@ExperimentalCoilApi
@Composable
fun FilteredDrinkItem(
    drink: Drink,
    navController: NavHostController
    //,
    //drinkImageDao: DrinkImageDao
) {
    /*
    val localImagePath by produceState<String?>(initialValue = null, key1 = drink.id) {
        val drinkImage = drinkImageDao.getDrinkImage(drink.id)
        value = drinkImage?.localImagePath
    }
     */
    /** pokud se to půjde bude použit obrázek z lokální databáze, tak se použije jinak se použije obrázek ze serveru */
    /** pokud to nebude možné bude místo něj použitý placeholder */
    /*
    val painter = rememberImagePainter(
        data = localImagePath ?: "${Constants.BASE_URL}${drink.image}",
        builder = {
            placeholder(R.drawable.ic_placeholder)
            error(R.drawable.ic_placeholder)
        }
    )
    */
    val painter = rememberImagePainter(data = "${Constants.BASE_URL}${drink.image}"){
        placeholder(R.drawable.ic_placeholder)
        error(R.drawable.ic_placeholder)
    }


    Box(modifier = Modifier
        .background(MaterialTheme.colors.drinksScreenBackgroundColor)
        .height(DRINK_ITEM_HEIGHT)
        .clickable {
            navController.navigate(Screen.DrinkDetails.passDrinkId(drinkId = drink.id))
        },
        contentAlignment = Alignment.BottomStart
    ){
        Surface(shape = RoundedCornerShape(size = LARGE_PADDING)){
            Image(
                modifier = Modifier
                    .fillMaxWidth(),
                painter = painter,
                contentDescription = stringResource(R.string.drink_image),
                contentScale = ContentScale.Crop
            )
        }
        Surface(
            modifier = Modifier
                .fillMaxHeight(0.4f)
                .fillMaxWidth(),
            color = Color.Black.copy(alpha = ContentAlpha.medium),
            shape = RoundedCornerShape(
                bottomStart = LARGE_PADDING,
                bottomEnd = LARGE_PADDING
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = MEDIUM_PADDING, top = MEDIUM_PADDING, end = MEDIUM_PADDING)
            ) {
                Text(
                    text = drink.name,
                    color = MaterialTheme.colors.topAppBarContentColor,
                    fontSize = MaterialTheme.typography.h5.fontSize,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = drink.description,
                    color = Color.White.copy(alpha = ContentAlpha.medium),
                    fontSize = MaterialTheme.typography.subtitle1.fontSize,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
                /*
                Row(
                    modifier = Modifier.padding(top = SMALL_PADDING),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RatingWidget(
                        modifier = Modifier.padding(end = SMALL_PADDING),
                        rating = drink.rating
                    )
                    Text(
                        text = "(${drink.rating})",
                        textAlign = TextAlign.Center,
                        color = Color.White.copy(alpha = ContentAlpha.medium)
                    )
                }
                */
            }
        }
    }
}