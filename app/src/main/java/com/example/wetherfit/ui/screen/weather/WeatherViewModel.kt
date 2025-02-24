package com.example.wetherfit.ui.screen.weather

import androidx.compose.runtime.MutableIntState
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

internal sealed class WeatherUiEvent

internal class WeatherViewModel(
    private val getWeather: GetWeatherUseCase
) : ViewModel() {
    private val _uiState: MutableStateFlow<WeatherUiState> =
        MutableStateFlow(WeatherUiState.Loading)

    val uiState = _uiState.asStateFlow()

    fun handleUiEvent(uiEvent: WeatherUiEvent) {
        viewModelScope.launch {
            when (uiEvent) {
                else -> Unit
            }
        }
    }

    init {
        viewModelScope.launch {
            getWeather.invoke()
        }
    }

}