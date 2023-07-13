package com.example.drinkapp.presentation.screens.about_app

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import com.example.drinkapp.R
import com.example.drinkapp.presentation.screens.TopBar
import com.example.drinkapp.ui.theme.DarkBlue
import com.example.drinkapp.ui.theme.LightBlue
import com.example.drinkapp.ui.theme.aboutAppBackgroundColor
import com.example.drinkapp.ui.theme.aboutAppTextColor
import com.example.drinkapp.util.Constants
import com.example.drinkapp.util.Constants.APP_VERSION
import kotlinx.coroutines.CoroutineScope

@ExperimentalCoilApi
@Composable
fun AboutAppScreen(
    scaffoldState: ScaffoldState,
    scope: CoroutineScope
) {

    val logoFont = FontFamily(
        Font(R.font.bagel_fat_one_regular)
    )

    val mContext = LocalContext.current

    Scaffold(
        topBar = {
                 TopBar(
                     text = "",
                     scope = scope,
                     scaffoldState = scaffoldState,
                     search = false
                 ) {

                 }
        },
        content = {
            Column(
                modifier = Modifier
                    .background(color = MaterialTheme.colors.aboutAppBackgroundColor)
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .background(Brush.verticalGradient(listOf(DarkBlue, LightBlue)))
                    //,
                    //verticalAlignment = Alignment.CenterVertically,
                    //horizontalArrangement = Arrangement.Center
                ){
                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = "",
                        modifier = Modifier
                            .height(120.dp)
                            .fillMaxWidth()
                            .padding(top = 30.dp)
                    )

                    Text(
                        modifier = Modifier
                            .padding(top = 140.dp)
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        text = "Cocktail Guru",
                        color = Color.White,
                        fontSize = 30.sp,
                        fontFamily = logoFont
                    )
                }

                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(10.dp)
                )

                Text(
                    text = "verze aplikace: $APP_VERSION",
                    color = MaterialTheme.colors.aboutAppTextColor,
                    modifier = Modifier
                        .padding(12.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center
                )

                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(10.dp)
                )

                Text(
                    text = "Díky za používání Cocktail Guru!",
                    color = MaterialTheme.colors.aboutAppTextColor,
                    modifier = Modifier
                        .padding(12.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center
                )

                Text(
                    text = "Tady můžeš aplikaci sdílet:",
                    color = MaterialTheme.colors.aboutAppTextColor,
                    modifier = Modifier
                        .padding(12.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Button(
                        colors = ButtonDefaults.buttonColors(backgroundColor = DarkBlue),
                        onClick = {
                            Toast.makeText(mContext, "Funkce sdílení zatím není dostupná", Toast.LENGTH_SHORT).show()
                        }
                    ) {
                        Text(
                            text = "Sdílet",
                            color = Color.White
                        )
                    }
                }
            }
        },
        backgroundColor = MaterialTheme.colors.aboutAppBackgroundColor
    )
}