package com.example.city_weather.api


import androidx.lifecycle.LiveData
import com.example.city_weather.model.WeatherModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

//https://yahoo-weather5.p.rapidapi.com/weather?location=Dushanbe&format=json&u=c

interface WeatherAPI {

    @GET("weather")
    fun getWeatherData(
        @Query(QUERY_PARAM_LOCATION) city: String,
        @Query(QUERY_PARAM_FORMAT) format: String = "json",
        @Query(QUERY_PARAM_U) u: String = "c"   ,

        @Header("x-rapidapi-host") value1:String="yahoo-weather5.p.rapidapi.com",
        @Header("x-rapidapi-key") value2: String = "eb1df13b9bmsh8dbe38b1911a0e2p140ba8jsnfd45a3f6dbe6"
    ) : Call<WeatherModel>

    companion object{
        private const val QUERY_PARAM_LOCATION="location"
        private const val QUERY_PARAM_FORMAT="format"
        private const val QUERY_PARAM_U="u"
    }


}