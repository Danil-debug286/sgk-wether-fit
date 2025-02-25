package com.example.data.storage.remote

import com.example.data.Storage
import com.example.data.storage.remote.HttpClient.client
import kotlinx.serialization.json.Json

internal class StorageImpl : Storage.Remote {
    override suspend fun getWeatherResponse(location: String): Result<WeatherResponse> {
        return try {
            Result.success(Json.decodeFromString(client.getWeather(location = location)))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}