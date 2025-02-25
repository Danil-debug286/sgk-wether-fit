package com.example.wetherfit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.wetherfit.ui.screen.weather.WeatherScreen
import com.example.wetherfit.ui.screen.weather.WeatherViewModel
import com.example.wetherfit.ui.theme.WetherFitTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val weatherViewModel: WeatherViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WetherFitTheme {
                WeatherScreen(viewModel = weatherViewModel)
            }
        }
    }
}
