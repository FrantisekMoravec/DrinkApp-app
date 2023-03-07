package com.example.drinkapp.navigation.graphs

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import coil.annotation.ExperimentalCoilApi
import com.example.drinkapp.navigation.HomeScreen
import com.example.drinkapp.navigation.Screen
import com.example.drinkapp.presentation.screens.splash.SplashScreen
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalMaterialApi
@ExperimentalCoilApi
@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun RootNavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Screen.Splash.route
    ) {
        //HomeNavGraph(navController = navController)
        composable(route = Graph.HOME) {
            HomeScreen()
        }
        composable(route = Screen.Splash.route) {
            SplashScreen(navController = navController)
        }
    }
}

/** cesta k navGrafům */
object Graph {
    const val ROOT = "root_graph"
    const val HOME = "home_graph" /** home graph zobrazuje MainScreen fragment spolu se spodní navigační lištou a v něm zabrazuje další fragmenty */
    //const val DETAILS = "details_graph"
}