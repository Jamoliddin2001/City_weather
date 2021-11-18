package com.example.city_weather.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.city_weather.api.Weather
import com.example.city_weather.model.WeatherModel
import com.example.city_weather.utils.Resource

class SharedViewModel: ViewModel() {
    private val weather=Weather

    fun getWeathersDATA(city:String):Resource<LiveData<WeatherModel>>{
        return weather.getWeathersData(city)
    }

}