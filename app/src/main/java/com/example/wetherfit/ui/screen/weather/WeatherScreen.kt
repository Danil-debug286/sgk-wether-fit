package com.example.wetherfit.ui.screen.weather

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.location.Location
import android.net.Uri
import android.provider.Settings
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.dmain.model.Weather
import com.example.wetherfit.R
import com.example.wetherfit.mapper.text
import com.example.wetherfit.ui.components.DefaultAsyncImage
import com.example.wetherfit.ui.components.LocationPermissionDialog
import com.example.wetherfit.ui.theme.WetherFitTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.android.gms.location.LocationServices
import com.example.wetherfit.ui.screen.weather.WeatherUiEvent as Event
import com.example.wetherfit.ui.screen.weather.WeatherUiState as State

@Composable
internal fun WeatherScreen(
    viewModel: WeatherViewModel
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    WeatherScreen(
        state = state,
        onEvent = viewModel::handleUiEvent,
    )
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
private fun WeatherScreen(
    state: State,
    onEvent: (Event) -> Unit,
) {
    val context = LocalContext.current
    val locationPermissionState = rememberMultiplePermissionsState(
        permissions = listOf(
            android.Manifest.permission.ACCESS_FINE_LOCATION,
            android.Manifest.permission.ACCESS_COARSE_LOCATION,
        )
    )
    var showPermissionDialog by rememberSaveable { mutableStateOf(true) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.onBackground,
    ) { innerPadding ->
        val screenModifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)

        when (state) {
            is State.Loading -> LoadingScreen(modifier = screenModifier)
            is State.Success -> Content(
                modifier = screenModifier,
                state = state,
            )
        }
    }

    if (!locationPermissionState.allPermissionsGranted and showPermissionDialog) LocationPermissionDialog(
        isPermissionDenied = locationPermissionState.shouldShowRationale,
        onAccess = {
            showPermissionDialog = false
            if (locationPermissionState.shouldShowRationale) openAppSettings(context)
            else locationPermissionState.launchMultiplePermissionRequest()
        },
        onDismiss = { showPermissionDialog = false },
    )

    LaunchedEffect(locationPermissionState) {
        if (locationPermissionState.allPermissionsGranted) getUserLocation(context) { result ->
            result.onSuccess { onEvent.invoke(Event.SetLocation(it)) }
        }
    }
}

@Composable
private fun LoadingScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator(modifier = Modifier.size(64.dp))
    }
}

@Composable
private fun Content(
    modifier: Modifier = Modifier,
    state: State.Success,
) {
    val context = LocalContext.current
    Column(
        modifier = modifier.padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = state.weather.conditions.text(),
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.width(16.dp))
            DefaultAsyncImage(
                modifier = Modifier.size(32.dp),
                model = state.weather.iconUrl,
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(
                id = R.string.weather_screen_temperature_description,
                state.weather.temperature
            ),
            style = MaterialTheme.typography.labelMedium,
        )
        Spacer(modifier = Modifier.height(16.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(horizontal = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            val workoutMessage = remember(state) {
                when (state.weather.conditions) {
                    Weather.Condition.Precipitation -> context.getString(R.string.weather_screen_workout_precipitation_message)
                    Weather.Condition.NoPrecipitation -> context.getString(R.string.weather_screen_workout_no_precipitation_message)
                }
            }
            Text(
                text = workoutMessage,
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center,
            )
        }
    }
}

private fun openAppSettings(context: Context) {
    val packageName = context.packageName
    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
    val uri = Uri.fromParts("package", packageName, null)
    intent.data = uri
    context.startActivity(intent)
}

@SuppressLint("MissingPermission")
private fun getUserLocation(
    context: Context,
    onResult: (Result<Location>) -> Unit,
) {
    val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
    try {
        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            if (location != null) {
                onResult.invoke(Result.success(location))
            } else {
                onResult.invoke(Result.failure(Exception("Location is null")))
            }
        }
    } catch (e: Exception) {
        onResult.invoke(Result.failure(e))
    }
}

@Preview(name = "light", showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "dark", showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun WeatherScreenPreview() {
    WetherFitTheme {
        WeatherScreen(
            state = State.Success(
                weather = Weather(
                    temperature = 0.0,
                    conditions = Weather.Condition.NoPrecipitation,
                    iconUrl = ""
                )
            ),
            onEvent = {}
        )
    }
}