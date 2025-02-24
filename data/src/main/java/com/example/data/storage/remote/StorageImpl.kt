package com.example.data.storage.remote

import com.example.data.Storage
import com.example.data.storage.remote.HttpClient.client


internal class StorageImpl : Storage.Remote {
    override suspend fun getWeatherResponse(location: String): Result<WeatherResponse> {
        return try {
            Result.success(client.getWeather(location = location))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}