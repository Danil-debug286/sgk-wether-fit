package com.example.dmain.model

data class Weather(
    val temperature: Double,
    val conditions: Condition, // "sunny", "rainy", etc.
    val iconUrl: String,
) {
    enum class Condition {
        Precipitation,
        NoPrecipitation,
    }
}
