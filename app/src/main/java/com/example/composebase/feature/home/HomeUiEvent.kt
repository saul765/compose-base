package com.example.composebase.feature.home

sealed class HomeUiEvent {
    data object OnStart : HomeUiEvent()
    data object OnRefresh : HomeUiEvent()
}
