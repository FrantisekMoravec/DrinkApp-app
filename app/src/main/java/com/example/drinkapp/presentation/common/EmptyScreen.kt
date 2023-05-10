package com.example.drinkapp.presentation.common

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.paging.LoadState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.example.drinkapp.R
import com.example.drinkapp.domain.model.Drink
import com.example.drinkapp.domain.model.Ingredient
import com.example.drinkapp.ui.theme.NETWORK_ERROR_ICON_HEIGHT
import com.example.drinkapp.ui.theme.SMALL_PADDING
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import java.net.ConnectException
import java.net.SocketTimeoutException

/** metoda pro vykreslení obrazovky bez obsahu */
@Composable
fun EmptyScreen(
    error: LoadState.Error? = null,
    drinks: LazyPagingItems<Drink>? = null,
    ingredients: LazyPagingItems<Ingredient>? = null
) {

    /** ikona a hláška pro obrazovku s vyhledáváním drinků podle jména */
    var message by remember {
        mutableStateOf("Najdi svůj drink")
    }
    var icon by remember {
        mutableStateOf(R.drawable.ic_search_document)
    }

    /** pokud nastane chyby během načítání obsahu tak se zobrazí o jakou chybu jde */
    if (error != null){
        message = parseErrorMessage(error = error)
        icon = R.drawable.ic_network_error
    }

    /** animace ikony */
    var startAnimation by remember { mutableStateOf(false) }
    val alphaAnim by animateFloatAsState(
        targetValue = if (startAnimation) ContentAlpha.disabled else 0f,
        animationSpec = tween(
            durationMillis = 1000
        )
    )
    LaunchedEffect(key1 = true){
        startAnimation = true
    }

    EmptyContent(
        alphaAnim = alphaAnim,
        icon = icon,
        message = message,
        drinks = drinks,
        ingredients = ingredients,
        error = error
    )
}

/** vykreslení textu a ikony */
@Composable
fun EmptyContent(
    alphaAnim: Float,
    icon: Int,
    message: String,
    drinks: LazyPagingItems<Drink>? = null,
    ingredients: LazyPagingItems<Ingredient>? = null,
    error: LoadState.Error? = null
) {
    var isRefreshing by remember { mutableStateOf(false) }

    SwipeRefresh(
        swipeEnabled = error != null,
        state = rememberSwipeRefreshState(isRefreshing = isRefreshing),
        onRefresh = {
            isRefreshing = true
            drinks?.refresh()
            ingredients?.refresh()
            isRefreshing = false
        }
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
                .padding(top = 120.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                modifier = Modifier
                    .alpha(alpha = alphaAnim)
                    .size(NETWORK_ERROR_ICON_HEIGHT),
                painter = painterResource(id = icon),
                contentDescription = stringResource(R.string.network_error_icon),
                tint = if (isSystemInDarkTheme()) Color.LightGray else Color.DarkGray
            )
            Text(
                modifier = Modifier
                    .alpha(alpha = alphaAnim)
                    .padding(top = SMALL_PADDING),
                text = message,
                color = if (isSystemInDarkTheme()) Color.LightGray else Color.DarkGray,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Medium,
                fontSize = MaterialTheme.typography.subtitle1.fontSize
            )
        }
    }
}
/** podle chyby vybere zprávu která se zobrazí uživateli */
fun parseErrorMessage(error: LoadState.Error): String{
    return when (error.error) {
        is SocketTimeoutException -> {
            "Nelze se připojit k serveru"
        }
        is ConnectException -> {
            "Nelze se připojit k internetu"
        }
        else -> {
            "Neznámá chyba"
        }
    }
}
/** náhled obrazovky */
@Preview(showBackground = true)
@Composable
fun EmptyScreenPreview() {
    EmptyContent(
        alphaAnim = 1f,
        icon = R.drawable.ic_network_error,
        message = "Nelze se připojit k internetu"
    )
}

/** náhled obrazovky když je zařízení v tmavém módu */
@Composable
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
fun EmptyScreenDarkPreview() {
    EmptyContent(
        alphaAnim = 1f,
        icon = R.drawable.ic_network_error,
        message = "Nelze se připojit k internetu"
    )
}