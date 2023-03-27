package com.example.drinkapp.presentation.common

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.drinkapp.R
import com.example.drinkapp.domain.model.Ingredient
import com.example.drinkapp.navigation.Screen
import com.example.drinkapp.presentation.components.ShimmerEffect
import com.example.drinkapp.ui.theme.*
import com.example.drinkapp.util.Constants.BASE_URL

// TODO přidat list zaškrtnutých ingrediencí a udělat do fragmentu drinků komponentu
// TODO která se použije pokud budou nějaké drinky zašktrnuté pokud žádné zaškrtnuté
// TODO nebudou použije se komponenta pro všechny ingredience

@ExperimentalCoilApi
@Composable
fun ListIngredients(
    ingredients: LazyPagingItems<Ingredient>,
    navController: NavHostController
) {
    val result = handlePagingResult(ingredients = ingredients)

    //var checkedIngredients = listOf<Int>()

    if (result){
        LazyColumn(
            contentPadding = PaddingValues(all = SMALL_PADDING),
            verticalArrangement = Arrangement.spacedBy(SMALL_PADDING)
        ){
            items(
                items = ingredients,
                key = { ingredient ->
                    ingredient.id
                }
            ){ ingredient ->
                ingredient?.let {
                    IngredientItem(ingredient = it, navController = navController)
                }
            }
        }
    }
}

@Composable
fun handlePagingResult(
    ingredients: LazyPagingItems<Ingredient>
): Boolean {
    ingredients.apply {
        val error = when{
            loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
            loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
            loadState.append is LoadState.Error -> loadState.append as LoadState.Error
            else -> null
        }

        return when{

            loadState.refresh is LoadState.Loading -> {
                ShimmerEffect()
                false
            }

            error != null ->{
                EmptyScreen(error = error, ingredients = ingredients)
                false
            }

            ingredients.itemCount < 1 -> {
                EmptyScreen()
                false
            }

            else -> true
        }
    }
}

@ExperimentalCoilApi
@Composable
fun IngredientItem(
    ingredient: Ingredient,
    navController: NavHostController
) {
    val checkedState = remember{ mutableStateOf(false) }
    val painter = rememberImagePainter(data = "${BASE_URL}${ingredient.image}"){
        placeholder(R.drawable.ic_placeholder)
        error(R.drawable.ic_placeholder)
    }

    Box(modifier = Modifier
        .height(INGREDIENT_ITEM_HEIGHT)
        .fillMaxWidth()
            /*
        .clickable {
            navController.navigate(Screen.IngredientDetails.passIngredientId(ingredientId = ingredient.id))
        }
        */,
        contentAlignment = Alignment.BottomStart
    ){

    Surface(
        shape = RoundedCornerShape(size = LARGE_PADDING),
        color = Color.Black.copy(alpha = 0.1f)
    ) {
        Surface(shape = RoundedCornerShape(size = LARGE_PADDING)) {
            Image(
                modifier = Modifier
                    .fillMaxWidth(0.2f)
                //.padding(start = 50.dp)
                ,
                painter = painter,
                contentDescription = stringResource(R.string.ingredient_image),
                contentScale = ContentScale.Crop
            )
        }
        Surface(
            modifier = Modifier
                .fillMaxHeight(0.6f)
                .fillMaxWidth(),
            color = Color.Black.copy(alpha = 0f),
            shape = RoundedCornerShape(
                bottomStart = LARGE_PADDING,
                bottomEnd = LARGE_PADDING
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        bottom = SMALL_PADDING,
                        top = LARGE_PADDING,
                        end = MEDIUM_PADDING,
                        start = 100.dp
                    )
                //.padding(start = 50.dp, bottom = 25.dp, top = 25.dp)
            ) {
                Text(
                    text = ingredient.name,
                    //color = MaterialTheme.colors.topAppBarContentColor,
                    color = Color.Black,
                    fontSize = MaterialTheme.typography.h5.fontSize,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Checkbox(
                modifier = Modifier.padding(start = 300.dp, top = 40.dp),
                checked = checkedState.value,
                onCheckedChange = {checkedState.value = it}
            )
/*
            Checkbox(
                modifier = Modifier.padding(start = 300.dp, top = 40.dp),
                checked = ingredient.checked!!,
                onCheckedChange = {
                    checkedState.value = it
                    ingredient.checked = checkedState.value
                    //checkedIngredients.add()
                }
            )
*/
        }
    }


    }
}

@ExperimentalCoilApi
@Preview
@Composable
fun IngredientItemPreview() {
    IngredientItem(
        ingredient = Ingredient(
                id = 1,
                name = "Vodka",
                image = "",
                description = "Vodka je tvrdý bezbarvý alkoholický nápoj oblíbený po celém světě. Kvalita a chuť vodky se mohou lišit vlivem různých faktorů. Jedná se o to, že tento alkoholický nápoj se vyrábí z obilí, jehož specifické složení určuje výrobce. Některé druhy namísto obilí obsahují destilaci z brambor."
            //,
            //    checked = false
            ),
        navController = rememberNavController()
    )
}

@ExperimentalCoilApi
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun IngredientItemDarkPreview() {
    IngredientItem(
        ingredient = Ingredient(
            id = 1,
            name = "Vodka",
            image = "",
            description = "Vodka je tvrdý bezbarvý alkoholický nápoj oblíbený po celém světě. Kvalita a chuť vodky se mohou lišit vlivem různých faktorů. Jedná se o to, že tento alkoholický nápoj se vyrábí z obilí, jehož specifické složení určuje výrobce. Některé druhy namísto obilí obsahují destilaci z brambor."
            //,
            //checked = false
        ),
        navController = rememberNavController()
    )
}