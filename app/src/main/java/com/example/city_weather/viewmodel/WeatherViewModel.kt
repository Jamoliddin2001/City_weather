package com.example.city_weather.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.city_weather.api.Weather
import com.example.city_weather.model.WeatherModel
import com.example.city_weather.utils.Resource

class WeatherViewModel :ViewModel(){

    private var isLoading: Boolean by mutableStateOf(false)

    private val weather=Weather
    private val getWeatherData=MutableLiveData<WeatherModel>()

    fun getWeatherData(city:String):Resource<LiveData<WeatherModel>>{
        val result=weather.getWeathersData(city)

        if(result is Resource.Success){
            isLoading = true
            getWeatherData.value= result.data?.value
        }

        return result
    }
}