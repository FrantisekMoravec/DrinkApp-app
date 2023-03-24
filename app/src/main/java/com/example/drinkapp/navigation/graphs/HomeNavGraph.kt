package com.example.drinkapp.navigation.graphs

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import coil.annotation.ExperimentalCoilApi
import com.example.drinkapp.navigation.BottomBarScreen
import com.example.drinkapp.navigation.Screen
import com.example.drinkapp.presentation.screens.details.DrinkDetailsScreen
import com.example.drinkapp.presentation.screens.assistant.AssistantScreen
import com.example.drinkapp.presentation.screens.drinks.DrinksScreen
import com.example.drinkapp.presentation.screens.ingredients.IngredientsScreen
import com.example.drinkapp.presentation.screens.search_drinks.DrinksSearchScreen
import com.example.drinkapp.presentation.screens.search_ingredients.IngredientsSearchScreen
import com.example.drinkapp.presentation.screens.settings.SettingsScreen
import com.example.drinkapp.util.Constants.DRINK_DETAILS_ARGUMENT_KEY
import com.example.drinkapp.util.Constants.INGREDIENT_DETAILS_ARGUMENT_KEY
import com.google.accompanist.pager.ExperimentalPagerApi

/** tato metoda slouží k navigaci mezi fragmenty a díky NavHostControlleru má backstack */

@ExperimentalMaterialApi
@ExperimentalCoilApi
@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun HomeNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.HOME,
        startDestination = BottomBarScreen.Drinks.route
    ) {

        composable(route = BottomBarScreen.Drinks.route) {
            DrinksScreen(navController = navController)
        }

        composable(route = BottomBarScreen.Ingredients.route) {
            IngredientsScreen(navController = navController)
        }
        composable(route = BottomBarScreen.Assistant.route) {
            AssistantScreen(navController = navController)
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
/*
        composable(
            route = Screen.IngredientDetails.route,
            arguments = listOf(navArgument(INGREDIENT_DETAILS_ARGUMENT_KEY){
                type = NavType.IntType
            })
        ) {
            IngredientDetailsScreen(navController = navController)
        }
*/
    }
}