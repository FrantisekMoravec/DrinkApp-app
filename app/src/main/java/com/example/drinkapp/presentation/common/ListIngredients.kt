package com.example.drinkapp.presentation.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Checkbox
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.drinkapp.R
import com.example.drinkapp.domain.model.IngredientFamily
import com.example.drinkapp.presentation.components.ShimmerEffect
import com.example.drinkapp.ui.theme.INGREDIENT_ITEM_HEIGHT
import com.example.drinkapp.ui.theme.LARGE_PADDING
import com.example.drinkapp.ui.theme.MEDIUM_PADDING
import com.example.drinkapp.ui.theme.SMALL_PADDING
import com.example.drinkapp.ui.theme.ingredientBoxBackgroundColor
import com.example.drinkapp.ui.theme.ingredientNamesColor
import com.example.drinkapp.util.Constants.BASE_URL
import kotlinx.coroutines.flow.StateFlow

@ExperimentalCoilApi
@Composable
fun ListIngredients(
    ingredientFamilies: LazyPagingItems<IngredientFamily>,
    navController: NavHostController,
    checkedIngredients: StateFlow<Map<Int, String>>,
    onCheckedChange: (Int, String, Boolean) -> Unit
    //filteredDrinksViewModel: FilteredDrinksViewModel = hiltViewModel()
) {
    //val checkedIngredients = rememberSaveable { mutableStateOf(mutableSetOf<Ingredient>()) }

    val result = handlePagingResult(ingredientFamilies = ingredientFamilies)

    if (result){
        LazyColumn(
            contentPadding = PaddingValues(all = SMALL_PADDING),
            verticalArrangement = Arrangement.spacedBy(SMALL_PADDING)
        ){
            items(
                items = ingredientFamilies,
                key = { ingredient ->
                    ingredient.id
                }
            ) { ingredient ->
                ingredient?.let {
                    IngredientItem(
                        ingredientFamily = it,
                        navController = navController,
                        checkedIngredients = checkedIngredients,
                        onCheckedChange = onCheckedChange
                    )
                }
            }
        }
    }
}

@Composable
fun handlePagingResult(
    ingredientFamilies: LazyPagingItems<IngredientFamily>
): Boolean {
    ingredientFamilies.apply {
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
                EmptyScreen(error = error, ingredientFamilies = ingredientFamilies)
                false
            }

            ingredientFamilies.itemCount < 1 -> {
                EmptyScreen(error = null)
                false
            }

            else -> true
        }
    }
}

@ExperimentalCoilApi
@Composable
fun IngredientItem(
    ingredientFamily: IngredientFamily,
    navController: NavHostController,
    checkedIngredients: StateFlow<Map<Int, String>>,
    onCheckedChange: (Int, String, Boolean) -> Unit
) {
    val currentCheckedIngredients by checkedIngredients.collectAsState()

    val painter = rememberImagePainter(data = "${BASE_URL}${ingredientFamily.image}"){
        placeholder(R.drawable.ic_placeholder)
        error(R.drawable.ic_placeholder)
    }

    Box(modifier = Modifier
        .height(INGREDIENT_ITEM_HEIGHT)
        .fillMaxWidth()
        .clickable {
            //navController.navigate(Screen.IngredientDetails.passIngredientId(ingredientId = ingredient.id))
            //Log.d("ingredient name", ingredient.name + " - ingredient clicked")
        }
        ,
        contentAlignment = Alignment.BottomStart
    ){

    Surface(
        shape = RoundedCornerShape(size = LARGE_PADDING),
        color = MaterialTheme.colors.ingredientBoxBackgroundColor.copy(alpha = 0.1f)
    ) {
        Surface(shape = RoundedCornerShape(size = LARGE_PADDING)) {
            Image(
                modifier = Modifier
                    .fillMaxWidth(0.2f),
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
                    text = ingredientFamily.name,
                    color = MaterialTheme.colors.ingredientNamesColor,
                    fontSize = MaterialTheme.typography.h5.fontSize,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Checkbox(
                modifier = Modifier.padding(start = 300.dp, top = 40.dp),
                enabled = true,
                checked = currentCheckedIngredients.values.contains(ingredientFamily.name),
                onCheckedChange = { checked ->
                    onCheckedChange(ingredientFamily.id, ingredientFamily.name, checked)
                }
            )

        }
    }
    }
}
/*
@ExperimentalCoilApi
@Preview
@Composable
fun IngredientItemPreview() {
    IngredientItem(
        ingredient = Ingredient(
                id = 1,
                name = "Vodka",
                image = "",
                description = "Vodka je tvrdý bezbarvý alkoholický nápoj oblíbený po celém světě. Kvalita a chuť vodky se mohou lišit vlivem různých faktorů. Jedná se o to, že tento alkoholický nápoj se vyrábí z obilí, jehož specifické složení určuje výrobce. Některé druhy namísto obilí obsahují destilaci z brambor.",
                madeByUser = false
            ),
        navController = rememberNavController(),
        onCheckboxChanged = ("Vodka", false)
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
            description = "Vodka je tvrdý bezbarvý alkoholický nápoj oblíbený po celém světě. Kvalita a chuť vodky se mohou lišit vlivem různých faktorů. Jedná se o to, že tento alkoholický nápoj se vyrábí z obilí, jehož specifické složení určuje výrobce. Některé druhy namísto obilí obsahují destilaci z brambor.",
            madeByUser = false
        ),
        navController = rememberNavController()
    )
}
*/