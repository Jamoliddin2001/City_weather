package com.example.city_weather

import android.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Place
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.city_weather.ui.theme.Purple200
import com.example.city_weather.ui_programm.navigation.Screen
import com.example.city_weather.viewmodel.WeatherViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.karimsinouh.onBoarding.ui.theme.onBoarding.ChangTextTitile
import com.karimsinouh.onBoarding.ui.theme.onBoarding.PageWeather

@ExperimentalPagerApi
@Composable
//@Preview(showBackground = true)
fun Page(navController: NavController) {
    TopSection(navController)
}

@ExperimentalPagerApi
@Composable
fun TopSection(
    navController: NavController,
    viewModel: WeatherViewModel = viewModel()
) {

    val newCity=viewModel.searchCity.value
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .padding(10.dp)
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp),
            value = newCity,
            leadingIcon = { Icon(Icons.Sharp.Place, contentDescription = null) },
            onValueChange = { viewModel.searchCity.value=it },
            label = { Text(text = "Add your city") },
            shape = RoundedCornerShape(20.dp)
        )

        Surface(
            shape = RoundedCornerShape(10.dp)
        ) {
            Box(
                modifier = Modifier
                    .clickable {
//                        PageWeather(navController = navController,city = newCity)
                        navController.navigate(Screen.Home.rout)
                    }
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                colorResource(R.color.background_of_city2),
                                colorResource(R.color.background1)
                            )
                        )
                    )
                    .fillMaxWidth()
                    .size(60.dp)
                    .padding(10.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "OK",
                    fontSize = 25.sp,
                    color = colorResource(R.color.white)
                )
            }
        }



    }

}


@Preview
@Composable
fun Button() {
    Box(
        modifier = Modifier
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        colorResource(R.color.background_of_city2),
                        colorResource(R.color.background1)
                    )
                )
            )
            .fillMaxWidth()
            .size(100.dp)
            .padding(10.dp)
            .clip(RoundedCornerShape(12.dp)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "OK",
            fontSize = 25.sp,
            color = colorResource(R.color.white)
        )
    }


}
