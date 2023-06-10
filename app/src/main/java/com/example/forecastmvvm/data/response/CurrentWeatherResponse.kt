package com.example.forecastmvvm.data.response

import com.google.gson.annotations.SerializedName


data class CurrentWeatherResponse(
    @SerializedName("current")
    val current: CurrentWeatherEntry,
    val location: Location,
    val request: Request
)