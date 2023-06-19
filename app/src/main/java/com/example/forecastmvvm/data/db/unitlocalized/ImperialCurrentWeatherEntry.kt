package com.example.forecastmvvm.data.db.unitlocalized

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

data class ImperialCurrentWeatherEntry (

    @ColumnInfo(name = "temperature")
    override val temperature: Double,
    @ColumnInfo(name = "weatherDescriptions")
    override val weatherDescriptions: String,
    @ColumnInfo(name = "weatherIcons")
    override val weatherIcons: String,
    @ColumnInfo(name = "windSpeed")
    override val windSpeed: Double,
    @ColumnInfo(name = "windDir")
    override val windDir: String,
    @ColumnInfo(name = "precip")
    override val precip: Double,
    @ColumnInfo(name = "feelslike")
    override val feelslike: Double,
    @ColumnInfo(name = "visibility")
    override val visibility: Double

    /*@ColumnInfo(name ="cloudcover")
    override val cloudcover: Int,
    @ColumnInfo(name ="feelslike")
    override val feelslike: Int,
    @ColumnInfo(name ="humidity")
    override val humidity: Int,
    //@ColumnInfo(name ="observation_time")
    //override val observationTime: String,
    @ColumnInfo(name ="precip")
    override val precip: Int,
    @ColumnInfo(name ="pressure")
    override val pressure: Double,
    @ColumnInfo(name ="temperature")
    override val temperature: Double,
    //@ColumnInfo(name ="uv_index")
    //override val uvİndex: Int,
    @ColumnInfo(name ="visibility")
    override val visibility: Int,
    //@ColumnInfo(name ="weather_code")
    //override val weatherCode: Int,
    //@ColumnInfo(name ="weather_descriptions")
    //override val weatherDescriptions: String,
    //@ColumnInfo(name ="weather_icons")
    //override val weatherİcons: String,
    //@ColumnInfo(name ="wind_degree")
    //override val windDegree: Int,
    //@ColumnInfo(name ="wind_dir")
    //override val windDir: String,
    //@ColumnInfo(name ="wind_speed")
    //override val windSpeed: Double*/

):UnitSpecificCurrentWeatherEntry



