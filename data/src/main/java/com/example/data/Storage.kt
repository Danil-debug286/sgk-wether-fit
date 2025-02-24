package com.example.data

import com.example.data.storage.remote.WeatherResponse

internal sealed interface Storage {
    interface Remote: Storage {
        suspend fun getWeatherResponse(
            location: String
        ): Result<WeatherResponse>
    }
    interface Local: Storage
}