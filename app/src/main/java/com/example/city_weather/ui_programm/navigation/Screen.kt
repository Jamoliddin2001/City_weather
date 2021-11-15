package com.example.city_weather.ui_programm.navigation

sealed class Screen(val rout:String){
    object Home: Screen("Home_Screen")
    object AddNewCity: Screen("AddNewCity_Screen")
}
