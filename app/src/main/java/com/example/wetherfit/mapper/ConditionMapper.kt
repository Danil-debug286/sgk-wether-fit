package com.example.wetherfit.mapper

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.dmain.model.Weather
import com.example.wetherfit.R

@Composable
internal fun Weather.Condition.text(): String = when (this) {
    Weather.Condition.Precipitation -> stringResource(R.string.condition_precipitation_description)
    Weather.Condition.NoPrecipitation -> stringResource(R.string.condition_no_precipitation_description)
}