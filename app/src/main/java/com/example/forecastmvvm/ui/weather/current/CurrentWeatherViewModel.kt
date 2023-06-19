package com.example.forecastmvvm.ui.weather.current

import androidx.lifecycle.ViewModel
import com.example.forecastmvvm.data.provider.UnitProvider
import com.example.forecastmvvm.data.repository.ForecastRepository
import com.example.forecastmvvm.internal.UnitSystem
import com.example.forecastmvvm.internal.lazyDeferred

class CurrentWeatherViewModel(
    private val forecastRepository: ForecastRepository,
    private val unitProvider: UnitProvider
) : ViewModel() {
    private val unitSystem = UnitSystem.IMPERIAL // UnitProvider.getUnitSystem()

    val isImperial:Boolean
        get() = unitSystem == UnitSystem.IMPERIAL

    val weather by lazyDeferred {
        forecastRepository.getCurrentWeather(isImperial)
    }
}