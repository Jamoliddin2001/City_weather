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
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.rout
    ){
        composable(
            route = Screen.Home.rout
        ){
            PageWeather(navController)
        }
        composable(
            route = Screen.AddNewCity.rout
        ){
            Page(navController)
        }
    }
}