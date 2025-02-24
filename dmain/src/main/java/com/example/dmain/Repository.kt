package com.example.dmain

import com.example.dmain.model.Weather

interface Repository {
    suspend fun getWeather(location: String): Result<Weather>
}