package com.example.forecastmvvm.data.repository

import androidx.lifecycle.LiveData
import com.example.forecastmvvm.data.db.CurrentWeatherDao
import com.example.forecastmvvm.data.db.entity.CURRENT_WEATHER_ID
import com.example.forecastmvvm.data.db.entity.CurrentWeatherEntity
import com.example.forecastmvvm.data.db.unitlocalized.UnitSpecificCurrentWeatherEntry
import com.example.forecastmvvm.data.network.WeatherNetworkDataSource
import com.example.forecastmvvm.data.network.response.CurrentWeatherResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.threeten.bp.ZonedDateTime

class ForecastRepositoryImpl(
    private val currentWeatherDao: CurrentWeatherDao,
    private val weatherNetworkDataSource: WeatherNetworkDataSource
) : ForecastRepository {

    init{
        weatherNetworkDataSource.downloadCurrentWeather.observeForever{ newCurrentWeather ->
            persistFetchedCurrentWeather(newCurrentWeather)
        }
    }

    override suspend fun getCurrentWeather(imperial: Boolean): LiveData<out UnitSpecificCurrentWeatherEntry> {
        return withContext(Dispatchers.IO){
            initWeatherData()
            return@withContext if(imperial) currentWeatherDao.getWeatherImperial()
            else currentWeatherDao.getWeatherImperial()
        }
    }

    private fun persistFetchedCurrentWeather(fetchedWeather: CurrentWeatherResponse) {
        val current = fetchedWeather.current
        val fetchedWeatherEntity = CurrentWeatherEntity(
            CURRENT_WEATHER_ID,
            current.cloudcover,
            current.feelslike,
            current.humidity,
            current.observationTime,
            current.precip,
            current.pressure,
            current.temperature,
            current.uvİndex,
            current.visibility,
            current.weatherCode,
            current.weatherDescriptions[0],
            current.weatherIcons[0],
            current.windDegree,
            current.windDir,
            current.windSpeed
        )
        GlobalScope.launch(Dispatchers.IO) {
            currentWeatherDao.upsert(fetchedWeatherEntity)
        }
    }

    private suspend fun initWeatherData(){
        if(isFetchCurrentNeeded(ZonedDateTime.now().minusHours(1)))
            fetchCurrentWeather()
    }

    private suspend fun fetchCurrentWeather(){
        weatherNetworkDataSource.fetchCurrentWeather("New York")
    }

    private fun isFetchCurrentNeeded(lastFetchTime: ZonedDateTime): Boolean {
        val thirtyMinutesAgo = ZonedDateTime.now().minusMinutes(30)
        return lastFetchTime.isBefore(thirtyMinutesAgo)
    }
}