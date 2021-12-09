package com.example.city_weather.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.city_weather.api.Weather
import com.example.city_weather.model.WeatherModel
import com.example.city_weather.utils.Resource
import com.example.city_weather.utils.UiState

class WeatherViewModel : ViewModel() {

    var searchCity = mutableStateOf("Dushanbe")
    val isloading = Weather.loading.value

    private val weather = Weather
    private val _getWeatherData = mutableStateOf(UiState<WeatherModel>())
    val getWeatherData: State<UiState<WeatherModel>> = _getWeatherData

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
