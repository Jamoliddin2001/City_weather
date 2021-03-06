package com.example.city_weather.viewmodel

import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.city_weather.api.Weather
import com.example.city_weather.model.Astronomy
import com.example.city_weather.model.WeatherModel
import com.example.city_weather.utils.Resource
import com.example.city_weather.utils.UiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class WeatherViewModel() : ViewModel() {

    var searchCity = mutableStateOf("Dushanbe")
    val isloading = Weather.loading.value

    private val weather = Weather
    private val _getWeatherData = mutableStateOf(UiState<WeatherModel>())
    val getWeatherData: State<UiState<WeatherModel>> = _getWeatherData

//    init {
//        getWeatherData(searchCity.value)
//    }

    fun getWeatherData(city: String): Resource<LiveData<WeatherModel>> {

        val result = weather.getWeathersData(city)

//        when (result) {
//            is Resource.Loading -> {
//
////                UiState<WeatherModel>(isLoading = true)
//                Log.d("TAG", "getWeatherData: WorkedLoading")
//            }
//            is Resource.Success -> {
//                _getWeatherData.value = UiState(data = result.data?.value)
//                Log.d("TAG", "getWeatherData: WorkedSuccess")
//            }
//            is Resource.Error -> {
//                UiState<WeatherModel>(
//                    error = result.message
//                )
//                Log.d("TAG", "getWeatherData: WorkedError")
//            }
//        }

        return result

    }

}