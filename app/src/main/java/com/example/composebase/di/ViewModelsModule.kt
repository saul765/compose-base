package com.example.composebase.di

import com.example.composebase.MainViewModel
import com.example.composebase.feature.home.HomeViewModel
import com.example.composebase.feature.search.SearchViewModel
import com.example.composebase.feature.settings.SettingsViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

object ViewModelsModule {

    val module = module {

        viewModelOf(::MainViewModel)
        viewModelOf(::HomeViewModel)
        viewModelOf(::SettingsViewModel)
        viewModelOf(::SearchViewModel)

    }
}