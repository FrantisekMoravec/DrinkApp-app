package com.example.drinkapp.presentation.screens.add_drink

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.drinkapp.R
/*
@ExperimentalCoilApi
@Composable
fun AddDrink() {

    var imageUri by remember { mutableStateOf<Uri?>(null) }
    val context = LocalContext.current
    val bitmap = remember { mutableStateOf<Bitmap?>(null) }

    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
            imageUri = uri
        }

    Column(
        //modifier = Modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        imageUri?.let {
            if (Build.VERSION.SDK_INT < 28) {
                bitmap.value = MediaStore.Images
                    .Media.getBitmap(context.contentResolver, it)
            } else {
                val source = ImageDecoder.createSource(context.contentResolver, it)
                bitmap.value = ImageDecoder.decodeBitmap(source)
            }

            bitmap.value?.let { btm ->
                Image(
                    bitmap = btm.asImageBitmap(),
                    contentDescription = null,
                    modifier = Modifier
                        .size(400.dp)
                        .padding(20.dp)
                )
            }
        }

        val painter = rememberImagePainter(data = R.drawable.ic_placeholder)
        val drinkName = ""
        val drinkDescription = ""
        val drinkIngredients = ""
        val drinkTutorial = ""

        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painter,
            contentDescription = stringResource(R.string.drink_image),
            contentScale = ContentScale.Crop
        )
        Text(
            text = "Jméno drinku: $drinkName",
            color = Color.White.copy(alpha = ContentAlpha.medium),
            fontSize = MaterialTheme.typography.subtitle1.fontSize,
            maxLines = 1
        )
        Text(
            text = "Popis drinku: $drinkDescription",
            color = Color.White.copy(alpha = ContentAlpha.medium),
            fontSize = MaterialTheme.typography.subtitle1.fontSize,
            maxLines = 5
        )
        Text(
            text = "Ingredience drinku: $drinkIngredients",
            color = Color.White.copy(alpha = ContentAlpha.medium),
            fontSize = MaterialTheme.typography.subtitle1.fontSize
        )
        Text(
            text = "Návod na přípravu drinku: $drinkTutorial",
            color = Color.White.copy(alpha = ContentAlpha.medium),
            fontSize = MaterialTheme.typography.subtitle1.fontSize
        )
    }
}
*/