package com.example.forecastmvvm.data.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.forecastmvvm.data.network.response.CurrentWeatherResponse
import com.example.forecastmvvm.internal.NoConnectivityExceptions

class WeatherNetworkDataSourceImpl(
    private val apixuWeatherApiService: ApixuWeatherApiService
) : WeatherNetworkDataSource {

    private val downloadedCurrentWeather = MutableLiveData<CurrentWeatherResponse>()
    override val downloadCurrentWeather: LiveData<CurrentWeatherResponse>
        get() = downloadedCurrentWeather

    override suspend fun fetchCurrentWeather(location: String) {
        try{
            val fetchedCurrentWeather = apixuWeatherApiService
                .getCurrentWeather(location)
                .await()
            downloadedCurrentWeather.postValue(fetchedCurrentWeather)
        }catch (e: NoConnectivityExceptions)
        {
            e.printStackTrace()
        }
    }

}