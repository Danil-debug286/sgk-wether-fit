package com.example.wetherfit.di

import android.app.Application
import com.example.data.di.remoteStorageModule
import com.example.data.di.repositoryModule
import com.example.dmain.usecase.GetWeatherUseCase
import com.example.wetherfit.ui.screen.weather.WeatherViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

internal class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(listOf(remoteStorageModule, repositoryModule, domainModule, appModule))
        }
    }
}

private val domainModule = module {
    factory<GetWeatherUseCase> { GetWeatherUseCase(repository = get()) }
}

private val appModule = module {
    viewModel { WeatherViewModel(getWeather = get()) }
}