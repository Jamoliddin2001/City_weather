package com.example.city_weather

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.city_weather.api.Retrofit
import com.example.city_weather.api.Weather
import com.example.city_weather.ui.theme.City_weatherTheme
import com.example.city_weather.viewmodel.SharedViewModel
import com.example.city_weather.viewmodel.WeatherViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import io.reactivex.disposables.CompositeDisposable

class MainActivity : ComponentActivity() {

    lateinit var navCotroller: NavHostController
    private val compositeDisposable = CompositeDisposable()

    @ExperimentalPagerApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            City_weatherTheme {
                navCotroller = rememberNavController()
                SetupNavGraph(navController = navCotroller)
            }
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }
}