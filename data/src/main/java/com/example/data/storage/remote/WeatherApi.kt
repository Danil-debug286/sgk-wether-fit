package com.example.data.storage.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.appendEncodedPathSegments
import io.ktor.http.contentType

private const val API = "???????"
private const val VERSION = "v1"
private const val CHAPTER = "forecast.json"

internal suspend fun HttpClient.getWeather(location: String): String =
    get {
        url {
            appendEncodedPathSegments(VERSION, CHAPTER)
            parameters.append(name = "key", value = API)
            parameters.append(name = "q", value = location)
        }
        contentType(ContentType.Application.Json)
    }.body()