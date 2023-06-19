package com.example.forecastmvvm.data.db.unitlocalized

import androidx.room.ColumnInfo

interface UnitSpecificCurrentWeatherEntry {


    val temperature: Double
    val weatherDescriptions: String
    val weatherIcons: String
    val windSpeed: Double
    val windDir: String
    val precip: Double
    val feelslike: Double
    val visibility: Double
    /*val cloudcover: Int
    val feelslike: Int
    val humidity: Int
    val observationTime: String
    val precip: Int
    val pressure: Double
    val temperature: Double
    val uvİndex: Int
    val visibility: Int
    val weatherCode: Int
    val weatherDescriptions: String
    val weatherİcons: String
    val windDegree: Int
    val windDir: String
    val windSpeed: Double*/

}


