package com.example.city_weather.model


import com.google.gson.annotations.SerializedName

data class Wind(
    @SerializedName("chill")
    val chill: Int,
    @SerializedName("direction")
    val direction: Int,
    @SerializedName("speed")
    val speed: Double
)