package com.example.city_weather.api

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.city_weather.model.WeatherModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


object Weather {

    private val api = Retrofit.apiWeather

    fun getWeathersData(city: String): LiveData<WeatherModel> {
        val liveData = MutableLiveData<WeatherModel>()

        api.getWeatherData(city=city).enqueue(object : Callback<WeatherModel> {
            override fun onResponse(
                call: Call<WeatherModel>,
                response: Response<WeatherModel>
            ) {
                liveData.value = response.body()
            }

            override fun onFailure(call: Call<WeatherModel>, t: Throwable) {
                println("${t.message}")
            }
        })
        return liveData
    }

}