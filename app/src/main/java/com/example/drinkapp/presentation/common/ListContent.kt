package com.example.drinkapp.presentation.common

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import androidx.paging.compose.LazyPagingItems
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.drinkapp.R
import com.example.drinkapp.domain.model.Drink
import com.example.drinkapp.navigation.Screen
import com.example.drinkapp.presentation.components.RatingWidget
import com.example.drinkapp.ui.theme.*
import com.example.drinkapp.util.Constants.BASE_URL

@Composable
fun ListContent(
    drinks: LazyPagingItems<Drink>,
    navController: NavHostController
) {

}

@ExperimentalCoilApi
@Composable
fun DrinkItem(
    drink: Drink,
    navController: NavHostController
) {
    /** cesta k souboru na server*/
    val painter = rememberImagePainter(data = "$BASE_URL${drink.image}"){
        placeholder(R.drawable.placeholder)
        error(R.drawable.placeholder)
    }

    Box(modifier = Modifier
        .height(DRINK_ITEM_HEIGHT)
        .clickable {
            navController.navigate(Screen.Details.passDrinkId(drinkId = drink.id))
        },
        contentAlignment = Alignment.BottomStart
    ){
        Surface(shape = RoundedCornerShape(size = LARGE_PADDING)){
            Image(
                modifier= Modifier.fillMaxSize(),
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
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(all = MEDIUM_PADDING)) {
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
            }
        }
    }
}

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
            tutorial = "Ingredience opatrně nalijte do panáku skrze kávovou lžičku, tak, aby zůstaly nepromíchané. A to přesně v tomto pořadí: 1. likér Kahlúa, 2. likér Baileys, a nakonec 3. Grand Marnier či Absinth nebo Stroh . Těsně před konzumací zapálíme zapalovačem. Podáváme s tlustým brčkem."
        ),
        navController = rememberNavController()
    )
}

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
            tutorial = "Ingredience opatrně nalijte do panáku skrze kávovou lžičku, tak, aby zůstaly nepromíchané. A to přesně v tomto pořadí: 1. likér Kahlúa, 2. likér Baileys, a nakonec 3. Grand Marnier či Absinth nebo Stroh . Těsně před konzumací zapálíme zapalovačem. Podáváme s tlustým brčkem."
        ),
        navController = rememberNavController()
    )
}