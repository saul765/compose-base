package com.example.composebase.di


import com.example.composebase.presentation.MainViewModel
import com.example.composebase.presentation.feature.contries.detail.CountryDetailViewModel
import com.example.composebase.presentation.feature.home.HomeViewModel
import com.example.composebase.presentation.feature.settings.SettingsViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

object ViewModelsModule {

    val module = module {
        viewModelOf(::MainViewModel)
        viewModelOf(::HomeViewModel)
        viewModelOf(::SettingsViewModel)
        viewModelOf(::CountryDetailViewModel)
    }
}