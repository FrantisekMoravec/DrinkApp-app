package com.example.drinkapp.presentation.screens.ingredients

import android.content.res.Configuration
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.drinkapp.R
import com.example.drinkapp.ui.theme.topAppBarBackgroundColor
import com.example.drinkapp.ui.theme.topAppBarContentColor

/** tato metoda vykresluje horní lištu fragmentu s ingrediencemi */
@Composable
fun IngredientsTopBar(onSearchClicked: () -> Unit) {
    TopAppBar(
        title = {
            Text(
                text = "hledat ingredience",
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
        }
    )
}

/** náhled toho jak bude lišta vypadat */
@Preview
@Composable
fun IngredientsTopBarPreview() {
    IngredientsTopBar {}
}

/** náhled toho jak bude lišta vypadat v temném módu */
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun IngredientsTopBarDarkPreview() {
    IngredientsTopBar {}
}