package com.example.drinkapp.presentation.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi

//TODO přidat "o aplikaci" a theme switch

@ExperimentalCoilApi
@Composable
fun SettingsScreen(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "SETTINGS",
            fontSize = MaterialTheme.typography.h3.fontSize,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}

/*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi

@ExperimentalCoilApi
@Composable
fun SettingsScreen(
    navController: NavHostController,
    selectedTheme: MutableState<List<String>>,
    themeOptions: List<String>
) {

    Column {
        Text("Vyberte jednu možnost:")
        Row {
            themeOptions.forEach { option ->
                RadioButtonWithLabel(
                    text = option,
                    selectedTheme = selectedTheme.value,
                    onOptionSelected = { selectedTheme.value = it }
                )
            }
        }
    }
}
@ExperimentalCoilApi
@Composable
fun RadioButtonWithLabel(
    text: String,
    selectedTheme: String,
    onOptionSelected: (String) -> Unit
) {
    Row(Modifier.padding(8.dp)) {
        RadioButton(
            selected = text == selectedTheme,
            onClick = { onOptionSelected(text) }
        )
        Text(
            text = text,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}
 */