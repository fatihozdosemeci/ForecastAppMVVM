package com.example.forecastmvvm.data.provider

import com.example.forecastmvvm.data.db.entity.Location

interface LocationProvider {
    suspend fun hasLocationChanged(lastWeatherLocation: Location): Boolean
    suspend fun getPreferredLocationString(): String
}