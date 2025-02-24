package com.example.data.storage.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class   WeatherResponse(
    @SerialName("location") val location: Location,
    @SerialName("current") val current: Current,
    @SerialName("forecast") val forecast: Forecast,
)

@Serializable
internal data class Location(
    @SerialName("region") val region: String,
    @SerialName("name") val name: String,
    @SerialName("country") val country: String,
    @SerialName("lat") val lat: Double,
    @SerialName("lon") val lon: Double,
    @SerialName("tz_id") val tzId: String,
    @SerialName("localtime_epoch") val localtimeEpoch: Int,
    @SerialName("localtime") val localtime: String,
)

@Serializable
internal data class Current(
    @SerialName("last_updated_epoch") val lastUpdatedEpoch: Int,
    @SerialName("last_updated") val lastUpdated: String,
    @SerialName("temp_c") val tempC: Double,
    @SerialName("temp_f") val tempF: Double,
    @SerialName("is_day") val isDay: Int,
    @SerialName("condition") val condition: Condition,
    @SerialName("wind_mph") val windMph: Double,
    @SerialName("wind_kph") val windKph: Int,
    @SerialName("wind_degree") val windDegree: Int,
    @SerialName("wind_dir") val windDir: String,
    @SerialName("pressure_mb") val pressureMb: Int,
    @SerialName("pressure_in") val pressureIn: Double,
    @SerialName("precip_mm") val precipMm: Int,
    @SerialName("precip_in") val precipIn: Int,
    @SerialName("humidity") val humidity: Int,
    @SerialName("cloud") val cloud: Int,
    @SerialName("feelslike_c") val feelslikeC: Double,
    @SerialName("feelslike_f") val feelslikeF: Double,
    @SerialName("windchill_c") val windchillC: Double,
    @SerialName("windchill_f") val windchillF: Double,
    @SerialName("heatindex_c") val heatindexC: Double,
    @SerialName("heatindex_f") val heatindexF: Int,
    @SerialName("dewpoint_c") val dewpointC: Double,
    @SerialName("dewpoint_f") val dewpointF: Int,
    @SerialName("vis_km") val visKm: Int,
    @SerialName("vis_miles") val visMiles: Int,
    @SerialName("uv") val uv: Int,
    @SerialName("gust_mph") val gustMph: Double,
    @SerialName("gust_kph") val gustKph: Double,
)

@Serializable
internal data class Forecast(
    @SerialName("forecastday") val forecastday: List<Forecastday>,
)

@Serializable
internal data class Condition(
    @SerialName("text") val text: String,
    @SerialName("icon") val icon: String,
    @SerialName("code") val code: Int,
)

@Serializable
internal data class Forecastday(
    @SerialName("date") val date: String,
    @SerialName("date_epoch") val dateEpoch: Int,
    @SerialName("day") val day: Day,
)

@Serializable
internal data class Day(
    @SerialName("maxtemp_c") val maxtempC: Double,
    @SerialName("maxtemp_f") val maxtempF: Double,
    @SerialName("mintemp_c") val mintempC: Double,
    @SerialName("mintemp_f") val mintempF: Double,
    @SerialName("avgtemp_c") val avgtempC: Double,
    @SerialName("avgtemp_f") val avgtempF: Double,
    @SerialName("maxwind_mph") val maxwindMph: Double,
    @SerialName("maxwind_kph") val maxwindKph: Double,
    @SerialName("totalprecip_mm") val totalprecipMm: Int,
    @SerialName("totalprecip_in") val totalprecipIn: Int,
    @SerialName("totalsnow_cm") val totalsnowCm: Int,
    @SerialName("avgvis_km") val avgvisKm: Int,
    @SerialName("avgvis_miles") val avgvisMiles: Int,
    @SerialName("avghumidity") val avghumidity: Int,
    @SerialName("daily_will_it_rain") val dailyWillItRain: Int,
    @SerialName("daily_chance_of_rain") val dailyChanceOfRain: Int,
    @SerialName("daily_will_it_snow") val dailyWillItSnow: Int,
    @SerialName("daily_chance_of_snow") val dailyChanceOfSnow: Int,
    @SerialName("condition") val condition: Condition,
    @SerialName("uv") val uv: Double,
)