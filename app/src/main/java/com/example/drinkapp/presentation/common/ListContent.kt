package com.example.drinkapp.presentation.common

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.util.Log
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
import com.example.drinkapp.domain.model.Drink
import com.example.drinkapp.navigation.Screen
import com.example.drinkapp.presentation.components.RatingWidget
import com.example.drinkapp.presentation.components.ShimmerEffect
import com.example.drinkapp.ui.theme.*
import com.example.drinkapp.util.Constants.BASE_URL

/** tento soubor slouží k zobrazování obsahu */

@ExperimentalCoilApi
@Composable
fun ListContent(
    drinks: LazyPagingItems<Drink>,
    navController: NavHostController
) {
    val result = handlePagingResult(drinks = drinks)

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
                    DrinkItem(drink = it, navController = navController)
                }
            }
        }
    }
}

/** tato metoda říká co se má stát podle výsledků načítání */
@Composable
fun handlePagingResult(
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
fun DrinkItem(
    drink: Drink,
    navController: NavHostController
) {
    /** pokud se to půjde bude použit obrázek ze serveru použije se obrázek drinku */
    /** pokud to nebude možné bude místo něj použitý placeholder */
    val painter = rememberImagePainter(data = "$BASE_URL${drink.image}"){
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
                    //.fillMaxSize()
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
            }
        }
    }
}

/** tato metoda ukazuje náhled jak bude vypadat DrinkItem pokud se nenačte obrázek driku */

@ExperimentalCoilApi
@Preview
@Composable
fun DrinkItemPreview() {
    DrinkItem(
        drink = Drink(
            id = 1,
            name = "B52",
            image = "",
            description = "B52 drink je třívrstvý míchaný nápoj nazvaný podle amerického bombardéru používaného ve válce ve Vietnamu. Zvláštností tohoto drinku je, že se podává doslova hořící.",
            rating = 4.0,
            ingredients = listOf(
                "Kahlúa (3 cl)",
                "Baileys (2 cl)",
                "Grand Marnier nebo Absinth nebo Stroh (3 cl)"
            ),
            tutorial = "Ingredience opatrně nalijte do panáku skrze kávovou lžičku, tak, aby zůstaly nepromíchané. A to přesně v tomto pořadí: 1. likér Kahlúa, 2. likér Baileys, a nakonec 3. Grand Marnier či Absinth nebo Stroh . Těsně před konzumací zapálíme zapalovačem. Podáváme s tlustým brčkem.",
            madeByUser = false
        ),
        navController = rememberNavController()
    )
}

/** tato metoda má dělá to samé co DrinkItemPreview ale pro zařízení v tmavém módu */

@ExperimentalCoilApi
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun DrinkItemDarkPreview() {
    DrinkItem(
        drink = Drink(
            id = 1,
            name = "B52",
            image = "",
            description = "B52 drink je třívrstvý míchaný nápoj nazvaný podle amerického bombardéru používaného ve válce ve Vietnamu. Zvláštností tohoto drinku je, že se podává doslova hořící.",
            rating = 4.0,
            ingredients = listOf(
                "Kahlúa (3 cl)",
                "Baileys (2 cl)",
                "Grand Marnier nebo Absinth nebo Stroh (3 cl)"
            ),
            tutorial = "Ingredience opatrně nalijte do panáku skrze kávovou lžičku, tak, aby zůstaly nepromíchané. A to přesně v tomto pořadí: 1. likér Kahlúa, 2. likér Baileys, a nakonec 3. Grand Marnier či Absinth nebo Stroh . Těsně před konzumací zapálíme zapalovačem. Podáváme s tlustým brčkem.",
            madeByUser = false
        ),
        navController = rememberNavController()
    )
}