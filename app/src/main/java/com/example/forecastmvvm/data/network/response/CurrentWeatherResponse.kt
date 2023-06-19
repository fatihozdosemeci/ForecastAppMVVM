package com.example.forecastmvvm.data.network.response


import com.example.forecastmvvm.data.db.entity.CurrentWeatherEntry
import com.example.forecastmvvm.data.db.entity.Location
import com.example.forecastmvvm.data.db.entity.Request

data class CurrentWeatherResponse(
    val current: CurrentWeatherEntry,
    val location: Location,
    val request: Request
)