package com.example.drinkapp.navigation.graphs

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import coil.annotation.ExperimentalCoilApi
import com.example.drinkapp.navigation.BottomBarScreen
import com.example.drinkapp.presentation.screens.assistant.AssistantScreen
import com.example.drinkapp.presentation.screens.drinks.DrinksScreen
import com.example.drinkapp.presentation.screens.settings.SettingsScreen
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
        composable(route = BottomBarScreen.Assistant.route) {
            AssistantScreen(navController = navController)
        }
        composable(route = BottomBarScreen.Settings.route) {
            SettingsScreen(navController = navController)
        }
        //detailsNavGraph(navController = navController)
    }
}

/*
fun NavGraphBuilder.detailsNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.DETAILS,
        startDestination = DetailsScreen.Information.route
    ) {
        composable(route = DetailsScreen.Information.route) {
            ScreenContent(name = DetailsScreen.Information.route) {
                navController.navigate(DetailsScreen.Overview.route)
            }
        }
        composable(route = DetailsScreen.Overview.route) {
            ScreenContent(name = DetailsScreen.Overview.route) {
                navController.popBackStack(
                    route = DetailsScreen.Information.route,
                    inclusive = false
                )
            }
        }
    }
}

sealed class DetailsScreen(val route: String) {
    object Information : DetailsScreen(route = "INFORMATION")
    object Overview : DetailsScreen(route = "OVERVIEW")
}
*/