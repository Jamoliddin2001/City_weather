package com.example.city_weather.api


import com.example.city_weather.model.WeatherModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

//https://api.openweathermap.org/data/2.5/weather?q=Hisor&appid=62ff407093238d2ac8228fbf36a0ea34

interface WeatherAPI {

    @GET("2.5/weather")
    fun getWeatherData(
        @Query(QUERY_PARAM_API_KEY) apiKey:String="62ff407093238d2ac8228fbf36a0ea34",
        @Query(QUERY_PARAM_CITY) city:String
    ) : Call<WeatherModel>

    companion object{
        private const val QUERY_PARAM_CITY="q"
        private const val QUERY_PARAM_API_KEY="appid"
    }


}