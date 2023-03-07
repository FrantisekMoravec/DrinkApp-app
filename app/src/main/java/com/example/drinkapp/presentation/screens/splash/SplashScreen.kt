package com.example.drinkapp.presentation.screens.splash

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.example.drinkapp.R
import com.example.drinkapp.navigation.Screen
import com.example.drinkapp.navigation.graphs.Graph
import com.example.drinkapp.ui.theme.DarkBlue
import com.example.drinkapp.ui.theme.LightBlue
import kotlinx.coroutines.delay

/** tato metoda říká kdy se má zapnout metoda Splash a řídí její animaci */
@Composable
fun SplashScreen(navController: NavHostController) {
    var startAnimation by remember { mutableStateOf(false) }
    val alphaAnim = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(
            durationMillis = 3000
        )
    )

    LaunchedEffect(key1 = true) {
        startAnimation = true
        delay(4000)
        navController.popBackStack()
        navController.navigate(Graph.HOME)
    }
    Splash(alpha = alphaAnim.value)
}

/** tato metoda vykresluje splash screen */
@Composable
fun Splash(alpha: Float) {
    if (isSystemInDarkTheme()){
        Box(
            modifier = Modifier
                .background(Color.Black)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = stringResource(R.string.app_logo)
            )
        }
    }else{
        Box(
            modifier = Modifier
                .background(Brush.verticalGradient(listOf(DarkBlue, LightBlue)))
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = stringResource(R.string.app_logo)
            )
        }
    }
}

/** náhled splash screenu */
@Preview
@Composable
fun SplashScreenPreview() {
    Splash(alpha = 1f)
}

/** náhled splash screenu pro zařízení v tmavém módu*/
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun SplashScreenPreviewDark() {
    Splash(alpha = 1f)
}