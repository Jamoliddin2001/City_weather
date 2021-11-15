package com.example.city_weather.api

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
//https://api.openweathermap.org/data/2.5/weather?q=Hisor&appid=62ff407093238d2ac8228fbf36a0ea34
object Retrofit {

    private const val BASE_URL="https://api.openweathermap.org/data/"

    private val retrofit=Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    val apiWeather = retrofit.create(WeatherAPI::class.java)
}
