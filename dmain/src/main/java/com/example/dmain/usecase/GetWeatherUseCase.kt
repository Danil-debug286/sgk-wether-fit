package com.example.dmain.usecase

import com.example.dmain.Repository

class GetWeatherUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke(location: String) = repository.getWeather(location = location)
}