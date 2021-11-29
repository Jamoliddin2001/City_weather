package com.example.city_weather

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.city_weather.ui_programm.navigation.Screen
import com.google.accompanist.pager.ExperimentalPagerApi
import com.karimsinouh.onBoarding.ui.theme.onBoarding.PageWeather


@ExperimentalPagerApi
@Composable
fun SetupNavGraph(
    navController: NavHostController,
    isFirst: String? = "NO"
) {
    NavHost(
        navController = navController,
        startDestination = if(isFirst=="YES") Screen.AddNewCity.rout else Screen.Home.rout
    ) {
        composable(
            route = Screen.Home.rout
        ) {
            PageWeather(navController = navController)
        }
        composable(
            route = Screen.AddNewCity.rout
        ) {
            Page(navController)
        }
    }

}
