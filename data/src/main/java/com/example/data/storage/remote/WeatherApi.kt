package com.example.data.storage.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.appendEncodedPathSegments
import io.ktor.http.contentType

private const val WEATHER_BASE_URL = "http://api.weatherapi.com/v1/"
private const val API = "??????"

internal suspend fun HttpClient.getWeather(location: String): WeatherResponse =
    get {
        url {
            appendEncodedPathSegments(WEATHER_BASE_URL, "forecast.json")
            parameters.append(name = "key", value = API)
            parameters.append(name = "q", value = location)
        }
        contentType(ContentType.Application.Json)
    }.body()