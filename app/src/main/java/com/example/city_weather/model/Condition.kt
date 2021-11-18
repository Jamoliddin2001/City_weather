package com.example.city_weather.model


import com.google.gson.annotations.SerializedName

data class Condition(
    @SerializedName("code")
    val code: Int,
    @SerializedName("temperature")
    val temperature: Int,
    @SerializedName("text")
    val text: String
)