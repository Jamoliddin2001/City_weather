package com.example.city_weather.model


import com.google.gson.annotations.SerializedName

data class CurrentObservation(
    @SerializedName("astronomy")
    val astronomy: Astronomy,
    @SerializedName("atmosphere")
    val atmosphere: Atmosphere,
    @SerializedName("condition")
    val condition: Condition,
    @SerializedName("pubDate")
    val pubDate: Int,
    @SerializedName("wind")
    val wind: Wind
)