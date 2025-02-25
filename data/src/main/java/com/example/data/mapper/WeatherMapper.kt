package com.example.data.mapper

import com.example.data.storage.remote.WeatherResponse
import com.example.dmain.model.Weather

internal object WeatherMapper {

    fun WeatherResponse.toData(): Weather {
        val forecastday = forecast.forecastday?.firstOrNull()?.day
        val willItRain = forecastday?.dailyWillItRain == 1
        val willItSnow = forecastday?.dailyWillItSnow == 1
        return Weather(
            temperature = forecastday?.avgtempC ?: 0.0,
            conditions = if (willItSnow or willItRain) Weather.Condition.Precipitation
            else Weather.Condition.NoPrecipitation,
            iconUrl = "https:${forecastday?.condition?.icon ?: "//"}",
        )
    }
}