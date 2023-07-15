package com.example.drinkapp.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.drinkapp.R
import com.example.drinkapp.domain.model.Drink
import com.example.drinkapp.navigation.Screen
import com.example.drinkapp.ui.theme.DRINK_ITEM_HEIGHT
import com.example.drinkapp.ui.theme.LARGE_PADDING
import com.example.drinkapp.ui.theme.MEDIUM_PADDING
import com.example.drinkapp.ui.theme.SMALL_PADDING
import com.example.drinkapp.ui.theme.drinksScreenBackgroundColor
import com.example.drinkapp.ui.theme.topAppBarContentColor
import com.example.drinkapp.util.Constants
import com.example.drinkapp.util.Constants.BASE_URL

@ExperimentalCoilApi
@Composable
fun ListDrinks(
    drinks: LazyPagingItems<Drink>,
    navController: NavHostController
){
    LazyColumn(
        //modifier = Modifier
            //.background(MaterialTheme.colors.drinksScreenBackgroundColor),
        contentPadding = PaddingValues(all = SMALL_PADDING),
        verticalArrangement = Arrangement.spacedBy(SMALL_PADDING)
    ){
        items(
            items = drinks,
            key = { drink ->
                drink.id
            }
        ){drink ->
            drink?.let {
                SmallDrinkItem(
                    selectedDrink = it,
                    navController = navController
                )
            }
        }
    }
}

@ExperimentalCoilApi
@Composable
fun SmallDrinkItem(
    selectedDrink: Drink,
    navController: NavHostController
) {
    /** pokud se to půjde bude použit obrázek ze serveru použije se obrázek drinku */
    /** pokud to nebude možné bude místo něj použitý placeholder */
    val painter = rememberImagePainter(data = "${BASE_URL}${selectedDrink.image}"){
        placeholder(R.drawable.ic_placeholder)
        error(R.drawable.ic_placeholder)
    }

    Box(modifier = Modifier
        .background(MaterialTheme.colors.drinksScreenBackgroundColor)
        .height(DRINK_ITEM_HEIGHT)
        .clickable {
            navController.navigate(Screen.DrinkDetails.passDrinkId(drinkId = selectedDrink.id))
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
                .fillMaxHeight(0.3f)
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
                    text = selectedDrink.name,
                    color = MaterialTheme.colors.topAppBarContentColor,
                    fontSize = MaterialTheme.typography.h5.fontSize,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@ExperimentalCoilApi
@Preview
@Composable
fun SmallDrinkItemPreview() {
    SmallDrinkItem(
        selectedDrink = Drink(
            id = 2,
            name = "Bloody Mary",
            image = "",
            description = "Kdo by neznal krvavou Marii? Málokdo už však ví, že nápoj je pojmenován podle anglické královny Marie I. Tudorovny, která, jak už název drinku napoví, nebyla žádnou lidumilkou. Barva nápoje je krvavě červená, a po vodce, tabascu a pepři ostrá jako katova sekera.",
            ingredients = listOf(
                "Vodka (4,5 cl)",
                "Rajčatový džus (9 cl)",
                "Citronový džus (1,5 cl)",
                "Worcesterská omáčka",
                "Tabasco",
                "Sůl",
                "Pepř"
            ),
            tutorial = "Připravíme si sklenicí typu highball, do které nakapeme worcester, tabasco, přidáme špetku soli a pepře. Vložíme několik kostek ledu, nalijeme vodku a džusy v uvedeném množství. Nakonec vše lehce promícháme a ozdobíme plátkem citronu a stonkem celeru. Podáváme s brčkem.",
            madeByUser = false
        ),
        navController = rememberNavController()
    )
}