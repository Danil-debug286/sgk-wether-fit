package com.example.data.storage.remote

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonIgnoreUnknownKeys

@Serializable
internal data class WeatherResponse(
    @SerialName("location") val location: Location,
    @SerialName("current") val current: Current,
    @SerialName("forecast") val forecast: Forecast,
)

@Serializable
internal data class Location(
    @SerialName("region") val region: String? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("country") val country: String? = null,
    @SerialName("lat") val lat: Double? = null,
    @SerialName("lon") val lon: Double? = null,
    @SerialName("tz_id") val tzId: String? = null,
    @SerialName("localtime_epoch") val localtimeEpoch: Int? = null,
    @SerialName("localtime") val localtime: String? = null,
)

@Serializable
internal data class Current(
    @SerialName("last_updated_epoch") val lastUpdatedEpoch: Int? = null,
    @SerialName("last_updated") val lastUpdated: String? = null,
    @SerialName("temp_c") val tempC: Double? = null,
    @SerialName("temp_f") val tempF: Double? = null,
    @SerialName("is_day") val isDay: Int? = null,
    @SerialName("condition") val condition: Condition? = null,
    @SerialName("wind_mph") val windMph: Double? = null,
    @SerialName("wind_kph") val windKph: Double? = null,
    @SerialName("wind_degree") val windDegree: Double? = null,
    @SerialName("wind_dir") val windDir: String? = null,
    @SerialName("pressure_mb") val pressureMb: Double? = null,
    @SerialName("pressure_in") val pressureIn: Double? = null,
    @SerialName("precip_mm") val precipMm: Double? = null,
    @SerialName("precip_in") val precipIn: Double? = null,
    @SerialName("humidity") val humidity: Double? = null,
    @SerialName("cloud") val cloud: Int? = null,
    @SerialName("feelslike_c") val feelslikeC: Double? = null,
    @SerialName("feelslike_f") val feelslikeF: Double? = null,
    @SerialName("windchill_c") val windchillC: Double? = null,
    @SerialName("windchill_f") val windchillF: Double? = null,
    @SerialName("heatindex_c") val heatindexC: Double? = null,
    @SerialName("heatindex_f") val heatindexF: Double? = null,
    @SerialName("dewpoint_c") val dewpointC: Double? = null,
    @SerialName("dewpoint_f") val dewpointF: Double? = null,
    @SerialName("vis_km") val visKm: Double? = null,
    @SerialName("vis_miles") val visMiles: Double? = null,
    @SerialName("uv") val uv: Double? = null,
    @SerialName("gust_mph") val gustMph: Double? = null,
    @SerialName("gust_kph") val gustKph: Double? = null,
)

@Serializable
internal data class Forecast(
    @SerialName("forecastday") val forecastday: List<Forecastday>? = null,
)

@Serializable
internal data class Condition(
    @SerialName("text") val text: String? = null,
    @SerialName("icon") val icon: String? = null,
    @SerialName("code") val code: Int? = null,
)

@OptIn(ExperimentalSerializationApi::class)
@JsonIgnoreUnknownKeys
@Serializable
internal data class Forecastday(
    @SerialName("date") val date: String? = null,
    @SerialName("date_epoch") val dateEpoch: Long? = null,
    @SerialName("day") val day: Day? = null,
)

@Serializable
internal data class Day(
    @SerialName("maxtemp_c") val maxtempC: Double? = null,
    @SerialName("maxtemp_f") val maxtempF: Double? = null,
    @SerialName("mintemp_c") val mintempC: Double? = null,
    @SerialName("mintemp_f") val mintempF: Double? = null,
    @SerialName("avgtemp_c") val avgtempC: Double? = null,
    @SerialName("avgtemp_f") val avgtempF: Double? = null,
    @SerialName("maxwind_mph") val maxwindMph: Double? = null,
    @SerialName("maxwind_kph") val maxwindKph: Double? = null,
    @SerialName("totalprecip_mm") val totalprecipMm: Double? = null,
    @SerialName("totalprecip_in") val totalprecipIn: Double? = null,
    @SerialName("totalsnow_cm") val totalsnowCm: Double? = null,
    @SerialName("avgvis_km") val avgvisKm: Double? = null,
    @SerialName("avgvis_miles") val avgvisMiles: Double? = null,
    @SerialName("avghumidity") val avghumidity: Double? = null,
    @SerialName("daily_will_it_rain") val dailyWillItRain: Int? = null,
    @SerialName("daily_chance_of_rain") val dailyChanceOfRain: Int? = null,
    @SerialName("daily_will_it_snow") val dailyWillItSnow: Int? = null,
    @SerialName("daily_chance_of_snow") val dailyChanceOfSnow: Int? = null,
    @SerialName("condition") val condition: Condition? = null,
    @SerialName("uv") val uv: Double? = null,
)