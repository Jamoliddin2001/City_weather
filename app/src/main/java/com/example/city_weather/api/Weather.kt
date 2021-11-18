package com.example.city_weather.api

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.city_weather.model.WeatherModel
import com.example.city_weather.utils.Resource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


object Weather {

    private val api = Retrofit.apiWeather

    fun getWeathersData(city: String): Resource<LiveData<WeatherModel>> {
        val liveData = MutableLiveData<WeatherModel>()
        var i=false
        api.getWeatherData(city).enqueue(object : Callback<WeatherModel> {
            override fun onResponse(
                call: Call<WeatherModel>,
                response: Response<WeatherModel>
            ) {
                liveData.value = response.body()
            }
            override fun onFailure(call: Call<WeatherModel>, t: Throwable) {
                i=true
            }
        })
        if(i)return Resource.Error("ERROR!")
        return Resource.Success(liveData)
    }


}