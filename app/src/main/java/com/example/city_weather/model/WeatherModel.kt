package com.example.city_weather.model


import com.google.gson.annotations.SerializedName

data class WeatherModel(
    @SerializedName("current_observation")
    val currentObservation: CurrentObservation,
    @SerializedName("forecasts")
    val forecasts: List<Forecast>,
    @SerializedName("location")
    val location: Location
)