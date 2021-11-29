package com.example.city_weather

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.city_weather.ui.theme.City_weatherTheme
import com.google.accompanist.pager.ExperimentalPagerApi

class MainActivity : ComponentActivity() {

    private lateinit var navCotroller: NavHostController
    private lateinit var preferences:SharedPreferences
    private var isFirst :String? = null

    @ExperimentalPagerApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Check()
        setContent{
            City_weatherTheme {
                navCotroller = rememberNavController()
                SetupNavGraph(navController = navCotroller, isFirst = isFirst)
                Log.d("TAG", "onSetContent: $isFirst")
            }
        }
    }


    private fun Check(){
        preferences = getSharedPreferences("SearchPager", MODE_PRIVATE)

        isFirst = if(preferences.getString("isFirst",null) == null){
            preferences.edit().putString("isFirst","YES").apply()
            preferences.getString("isFirst",null)
        } else "NO"

    }
}