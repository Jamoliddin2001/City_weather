package com.example.city_weather.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.city_weather.api.Weather
import com.example.city_weather.model.WeatherModel

class SharedViewModel: ViewModel() {
    private val weather=Weather

    fun getWeathersData(city:String):LiveData<WeatherModel>{
        return weather.getWeathersData(city)
    }

}