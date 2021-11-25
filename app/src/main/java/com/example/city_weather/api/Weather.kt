package com.example.city_weather.api

import android.util.Log
import android.widget.ProgressBar
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.city_weather.R
import com.example.city_weather.model.WeatherModel
import com.example.city_weather.utils.Resource
import com.karimsinouh.onBoarding.ui.theme.onBoarding.CircularProgressBar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


object Weather {

    private val api = Retrofit.apiWeather
    val loading = mutableStateOf(true)

    fun getWeathersData(city: String): Resource<LiveData<WeatherModel>> {

        val liveData = MutableLiveData<WeatherModel>()
        var i = false
        api.getWeatherData(city).enqueue(object : Callback<WeatherModel> {
            override fun onResponse(
                call: Call<WeatherModel>,
                response: Response<WeatherModel>
            ) {
                if(response.isSuccessful){
                    liveData.value = response.body()
                    loading.value=false
                }
                Log.d("TAG", "onResponse: ${loading.value}")

            }

            override fun onFailure(call: Call<WeatherModel>, t: Throwable) {
                loading.value = false
                i = true
                Log.d("TAG", "onFailure: ${loading.value}")
            }
        })
        return if (i) Resource.Error("ERROR!")
        else Resource.Success(liveData)
    }


    fun getWeatherText(text: String): Int {
        var imageIcon: Int = 0
        when (text) {
            "Tornado" -> imageIcon = R.drawable.tornado
            "Tropical Storm" -> imageIcon = R.drawable.tornado
            "Breezy" -> imageIcon = R.drawable.breezy
            "Hurricane" -> imageIcon = R.drawable.tornado
            "Severe Thunderstorms" -> imageIcon = R.drawable.severethunderstorms
            "Thunderstorms" -> imageIcon = R.drawable.thunderstorms
            "Rain And Snow" -> imageIcon = R.drawable.rainandsnow
            "Rain And Sleet" -> imageIcon = R.drawable.rainandsnow
            "Snow And Sleet" -> imageIcon = R.drawable.rainandsnow
            "Mostly Sunny" -> imageIcon = R.drawable.mostlysunny
            "Freezing drizzle" -> imageIcon = R.drawable.freezingdrizzle
            "drizzle" -> imageIcon = R.drawable.drizzle
            "Freezing Rain" -> imageIcon = R.drawable.freezingrain
            "Showers" -> imageIcon = R.drawable.freezingrain
            "Snow Flurries" -> imageIcon = R.drawable.snowflurries
            "Snow Showers" -> imageIcon = R.drawable.snowshowers
            "Blowing Snow" -> imageIcon = R.drawable.blowingsnow
            "Snow" -> imageIcon = R.drawable.snow
            "Hail" -> imageIcon = R.drawable.hail
            "Sleet" -> imageIcon = R.drawable.sleet
            "Dust" -> imageIcon = R.drawable.dust
            "Foggy" -> imageIcon = R.drawable.foggy
            "Haze" -> imageIcon = R.drawable.haze
            "Smoky" -> imageIcon = R.drawable.dust
            "Blustery" -> imageIcon = R.drawable.blustery
            "Windy" -> imageIcon = R.drawable.windy
            "Cold" -> imageIcon = R.drawable.cold
            "Cloudy" -> imageIcon = R.drawable.mostlycloudy
            "Mostly Cloudy" -> imageIcon = R.drawable.mostlycloudy
            "Partly Cloudy" -> imageIcon = R.drawable.mostlycloudy
            "Clear (Night)" -> imageIcon = R.drawable.clearnight
            "Sunny" -> imageIcon = R.drawable.sunny
            "Fair" -> imageIcon = R.drawable.sunny
            "Rain And Hail" -> imageIcon = R.drawable.rainandhail
            "Rain" -> imageIcon = R.drawable.rain
            "Hot" -> imageIcon = R.drawable.hot
            "Isolated Thunderstorms" -> imageIcon = R.drawable.isolatedthunderstorms
            "Scattered Thunderstorms" -> imageIcon = R.drawable.scatteredthunderstorms
            "Scattered Showers" -> imageIcon = R.drawable.scatteredshowers
            "Heavy Snow" -> imageIcon = R.drawable.heavysnow
            "Thundershowers" -> imageIcon = R.drawable.thundershowers
            "Isolated Thundershowers" -> imageIcon = R.drawable.isolatedthunderstorms
            "Not Available" -> imageIcon = R.drawable.notavailable
            else -> imageIcon = R.drawable.nodataweather
        }

        return imageIcon
    }


}