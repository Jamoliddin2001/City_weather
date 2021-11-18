package com.example.city_weather.model


import com.google.gson.annotations.SerializedName

data class Astronomy(
    @SerializedName("sunrise")
    val sunrise: String,
    @SerializedName("sunset")
    val sunset: String
)