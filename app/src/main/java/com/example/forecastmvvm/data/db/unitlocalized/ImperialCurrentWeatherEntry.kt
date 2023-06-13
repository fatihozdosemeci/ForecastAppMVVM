package com.example.forecastmvvm.data.db.unitlocalized

import androidx.room.ColumnInfo

class ImperialCurrentWeatherEntry (
    @ColumnInfo(name = "temp")
    override val temperature: Double,
    @ColumnInfo(name = "condition_text")
    override val conditionText: String,
    @ColumnInfo(name = "condition_icon")
    override val conditionIconUrl: String,
    @ColumnInfo(name = "windSpeed")
    override val windSpeed: Double,
    @ColumnInfo(name = "windDir")
    override val windDirection: String,
    @ColumnInfo(name = "precipIn")
    override val precipitationVolume: Double,
    @ColumnInfo(name = "feelsLLikeTemp")
    override val feelsLikeTemperature: Double,
    @ColumnInfo(name = "visDist")
    override val visibilityDistance: Double

):UnitSpecificCurrentWeatherEntry