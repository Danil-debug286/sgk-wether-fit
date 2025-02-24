package com.example.data

import com.example.data.mapper.WeatherMapper.toData
import com.example.dmain.Repository
import com.example.dmain.model.Weather

internal class RepositoryImpl(
    private val remoteStorage: Storage.Remote
): Repository {
    override suspend fun getWeather(location: String): Result<Weather> {
        val weatherResponse = remoteStorage.getWeatherResponse(location = location)
        return weatherResponse.map { it.toData() }
    }
}