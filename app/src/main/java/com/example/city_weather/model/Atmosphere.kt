package com.example.city_weather.model


import com.google.gson.annotations.SerializedName

data class Atmosphere(
    @SerializedName("humidity")
    val humidity: Int,
    @SerializedName("pressure")
    val pressure: Int,
    @SerializedName("rising")
    val rising: Int,
    @SerializedName("visibility")
    val visibility: Double
)