package com.example.city_weather.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
//https://yahoo-weather5.p.rapidapi.com/weather?location=Dushanbe&format=json&u=c
object Retrofit {

    private const val BASE_URL="https://yahoo-weather5.p.rapidapi.com/"

    private val httpLoggingInterceptor=HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    private var okHttpClient=OkHttpClient.Builder()
        .addInterceptor(httpLoggingInterceptor)
        .build()

    private val retrofit=Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

    val apiWeather = retrofit.create(WeatherAPI::class.java)
}
