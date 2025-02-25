package com.example.data.storage.remote

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.ANDROID
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging

internal object HttpClient {

    private const val WEATHER_BASE_URL = "https://api.weatherapi.com"
    val client by lazy {
        HttpClient(OkHttp) {
            defaultRequest {
                url(WEATHER_BASE_URL)
            }
            install(Logging) {
                level = LogLevel.ALL
                logger = Logger.ANDROID
            }
        }
    }
}