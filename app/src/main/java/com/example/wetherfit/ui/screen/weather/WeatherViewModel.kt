package com.example.wetherfit.ui.screen.weather

import android.location.Location
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dmain.model.Weather
import com.example.dmain.usecase.GetWeatherUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

internal sealed class WeatherUiState {
    data object Loading : WeatherUiState()
    data class Success(val weather: Weather) : WeatherUiState()
}

internal sealed class WeatherUiEvent {
    data class SetLocation(val location: Location): WeatherUiEvent()
}

internal class WeatherViewModel(private val getWeather: GetWeatherUseCase) : ViewModel() {

    private val _uiState = MutableStateFlow<WeatherUiState>(WeatherUiState.Loading)
    val uiState = _uiState.asStateFlow()

    fun handleUiEvent(uiEvent: WeatherUiEvent) {
        viewModelScope.launch {
            when (uiEvent) {
                is WeatherUiEvent.SetLocation -> setLocation(location = uiEvent.location)
            }
        }
    }

    private suspend fun setLocation(location: Location) {
        getWeather.invoke(location = "${location.latitude},${location.longitude}")
            .onSuccess { _uiState.emit(WeatherUiState.Success(weather = it)) }
            .onFailure { Log.e("Weather", "setLocation: ", it) }
    }
}