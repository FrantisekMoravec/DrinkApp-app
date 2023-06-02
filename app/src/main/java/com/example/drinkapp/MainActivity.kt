package com.example.drinkapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import com.example.drinkapp.navigation.graphs.RootNavigationGraph
import com.example.drinkapp.ui.theme.DrinkAppTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.hilt.android.AndroidEntryPoint
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/** v této aktivitě se zobrazuje všechen obsah přes fragmenty */

@ExperimentalMaterialApi
@ExperimentalCoilApi
@ExperimentalAnimationApi
@ExperimentalPagerApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DrinkAppTheme {
                RootNavigationGraph(navController = rememberNavController())
            }

            /*
val contentLoaded = remember { mutableStateOf(false) }

if (contentLoaded.value) {
    DrinkAppTheme {
        RootNavigationGraph(navController = rememberNavController())
    }
}

LaunchedEffect(Unit) {
    lifecycleScope.launch {
        // Simulace asynchronního načítání (můžete nahradit skutečným asynchronním kódem)
        contentLoaded.value = true
    }
}
 */
        }
    }
}