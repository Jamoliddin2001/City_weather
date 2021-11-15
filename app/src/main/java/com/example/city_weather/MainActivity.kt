package com.example.city_weather

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.city_weather.api.Retrofit
import com.example.city_weather.ui.theme.City_weatherTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainActivity : ComponentActivity() {


    lateinit var navCotroller: NavHostController

    private val compositeDisposable = CompositeDisposable()


    @ExperimentalPagerApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            City_weatherTheme {
                // A surface container using the 'background' color from the theme
                navCotroller = rememberNavController()
                SetupNavGraph(navController = navCotroller)
            }
        }
//        val disposable = Retrofit.apiService.getWeatherData(city ="Hisor")
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe({
//                //ChangTextTitile((it.main.temp-273.15).toString())
//                Log.d("TEST_LOADING", it.toString())
//            },{
//                it.message?.let { it1 -> Log.d("TEST_ERROR_LOADING", it1) }
//            })
//        compositeDisposable.add(disposable)


    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }
}

