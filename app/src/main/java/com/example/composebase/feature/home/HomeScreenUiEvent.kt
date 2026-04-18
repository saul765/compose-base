package com.example.composebase.feature.home

sealed class HomeScreenUiEvent {
    data object OnPermissionGranted : HomeScreenUiEvent()
}
