package com.example.city_weather.model


import com.google.gson.annotations.SerializedName

data class Forecast(
    @SerializedName("code")
    val code: Int,
    @SerializedName("date")
    val date: Int,
    @SerializedName("day")
    var day: String,
    @SerializedName("high")
    val high: Int,
    @SerializedName("low")
    val low: Int,
    @SerializedName("text")
    val text: String
)