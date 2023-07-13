package com.example.drinkapp.navigation.graphs

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import coil.annotation.ExperimentalCoilApi
import com.example.drinkapp.navigation.BottomBarScreen
import com.example.drinkapp.navigation.Screen
import com.example.drinkapp.presentation.screens.about_app.AboutAppScreen
import com.example.drinkapp.presentation.screens.drink_details.DrinkDetailsScreen
import com.example.drinkapp.presentation.screens.drinks.DrinksScreen
import com.example.drinkapp.presentation.screens.ingredient_details.IngredientDetailsScreen
import com.example.drinkapp.presentation.screens.ingredients.FilteredDrinksScreen
import com.example.drinkapp.presentation.screens.ingredients.IngredientsScreen
import com.example.drinkapp.presentation.screens.search_drinks.DrinksSearchScreen
import com.example.drinkapp.presentation.screens.search_ingredients.IngredientsSearchScreen
import com.example.drinkapp.presentation.screens.settings.SettingsScreen
import com.example.drinkapp.util.Constants.DRINK_DETAILS_ARGUMENT_KEY
import com.example.drinkapp.util.Constants.INGREDIENT_DETAILS_ARGUMENT_KEY
import com.example.drinkapp.util.Constants.INGREDIENT_FAMILY_DETAILS_ARGUMENT_KEY
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.CoroutineScope

/** tato metoda slouží k navigaci mezi fragmenty a díky NavHostControlleru má backstack */

@ExperimentalMaterialApi
@ExperimentalCoilApi
@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun HomeNavGraph(
    navController: NavHostController,
    scaffoldState: ScaffoldState,
    scope: CoroutineScope
) {
    NavHost(
        navController = navController,
        route = Graph.HOME,
        startDestination = BottomBarScreen.Drinks.route
    ) {

        composable(route = BottomBarScreen.Drinks.route) {
            DrinksScreen(
                scope = scope,
                scaffoldState = scaffoldState,
                navController = navController
            )
        }

        composable(route = BottomBarScreen.Ingredients.route) {
            IngredientsScreen(
                scope = scope,
                scaffoldState = scaffoldState,
                navController = navController
            )
        }

        composable(route = BottomBarScreen.Settings.route) {
            SettingsScreen(navController = navController)
        }

        composable(route = Screen.DrinkSearch.route){
            DrinksSearchScreen(navController = navController)
        }

        composable(route = Screen.IngredientSearch.route){
            IngredientsSearchScreen(navController = navController)
        }

        composable(
            route = Screen.DrinkDetails.route,
            arguments = listOf(navArgument(DRINK_DETAILS_ARGUMENT_KEY){
                type = NavType.IntType
            })
        ) {
            DrinkDetailsScreen(navController = navController)
        }

        composable(
            route = Screen.IngredientDetails.route,
            arguments = listOf(navArgument(INGREDIENT_FAMILY_DETAILS_ARGUMENT_KEY){
                type = NavType.IntType
            })
        ){
            IngredientDetailsScreen(navController = navController)
        }

        composable(route = Screen.FilteredDrinks.route) {
            FilteredDrinksScreen(
                navController = navController,
                //drinks = drinks,
                scope = scope,
                scaffoldState = scaffoldState
            )
        }

        composable(route = Screen.AboutApp.route){
            AboutAppScreen(
                scaffoldState = scaffoldState,
                scope = scope
            )
        }

    }
}
