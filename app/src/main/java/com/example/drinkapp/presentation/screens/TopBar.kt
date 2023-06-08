package com.example.drinkapp.presentation.screens

import android.content.res.Configuration
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.drinkapp.R
import com.example.drinkapp.navigation.Screen
import com.example.drinkapp.ui.theme.topAppBarBackgroundColor
import com.example.drinkapp.ui.theme.topAppBarContentColor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/** tato metoda vykresluje horní lištu fragmentu s drinky */
@Composable
fun TopBar(
    text: String,
    scope: CoroutineScope,
    scaffoldState: ScaffoldState,
    onSearchClicked: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = text,
                color = MaterialTheme.colors.topAppBarContentColor
            )
        },
        backgroundColor = MaterialTheme.colors.topAppBarBackgroundColor,
        actions = {
            IconButton(onClick = onSearchClicked) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = stringResource(R.string.search_icon),
                    tint = MaterialTheme.colors.topAppBarContentColor
                )
            }
        },
        navigationIcon = {
            IconButton(onClick = {
                scope.launch {
                    scaffoldState.drawerState.open()
                }
            }) {
                Icon(
                    Icons.Filled.Menu,
                    contentDescription = "navigation drawer menu icon",
                    tint = MaterialTheme.colors.topAppBarContentColor
                )
            }
        }

    )
}
/*
/** náhled toho jak bude lišta vypadat */
@Preview
@Composable
fun TopBarPreview() {
    TopBar {}
}

/** náhled toho jak bude lišta vypadat v temném módu */
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun TopBarDarkPreview() {
    TopBar {}
}
*/