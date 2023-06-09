package com.example.drinkapp.navigation

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.drinkapp.R
import com.example.drinkapp.ui.theme.DarkBlue
import com.example.drinkapp.ui.theme.LightBlue
import com.example.drinkapp.ui.theme.navDrawerSelectedItemColor
import com.example.drinkapp.util.Constants.APP_VERSION
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun NavDrawer(
    scope: CoroutineScope,
    scaffoldState: ScaffoldState,
    navController: NavController
){

    val mContext = LocalContext.current

    val items = listOf(
        NavDrawerItem.Drinks,
        NavDrawerItem.Ingredients,
        NavDrawerItem.AddDrink,
        NavDrawerItem.AboutApp
    )

    Column(
        modifier = Modifier
            .background(color = Color.White)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .background(Brush.verticalGradient(listOf(DarkBlue, LightBlue))),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ){
//TODO sem dám místo loga jméno, příjmení, gmail a pfp
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "",
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth()
                    .padding(10.dp)
            )
        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(5.dp)
        )

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { items ->
            DrawerItem(
                item = items,
                selected = currentRoute == items.route,
                onItemClick = {

                    if (!items.isWorking)
                        Toast.makeText(mContext, "Funkce ${items.title.toLowerCase()} zatím není dostupná", Toast.LENGTH_LONG).show()
                    else{
                        navController.navigate(items.route){
                            navController.graph.startDestinationRoute?.let { route ->
                                popUpTo(route){
                                    saveState = true
                                }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }

                        scope.launch {
                            scaffoldState.drawerState.close()
                        }
                    }
                }
            )
        }

        Spacer(modifier = Modifier.weight(1f))
//TODO tohle upravit
        Text(
            text = APP_VERSION,
            color = Color.Black,
            modifier = Modifier
                .padding(12.dp)
        )

    }
}

@Composable
fun DrawerItem(
    item: NavDrawerItem,
    selected: Boolean,
    onItemClick: (NavDrawerItem) -> Unit)
{
    //pokud nebude item vybrán bude mít transparentní pozadí
    val background =
        if(selected)
            MaterialTheme.colors.navDrawerSelectedItemColor
        else
            Color(0, 0, 0, 0)

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick(item) }
            .height(45.dp)
            .background(background)
            .padding(start = 10.dp)
    ) {

        Image(
            painter = painterResource(id = item.icon),
            contentDescription = item.title,
            colorFilter = ColorFilter.tint(Color.Black),
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .height(24.dp)
                .width(24.dp)
        )
        Spacer(modifier = Modifier.width(7.dp))
        Text(
            text = item.title,
            fontSize = 16.sp,
            color = Color.Black
        )

    }

}