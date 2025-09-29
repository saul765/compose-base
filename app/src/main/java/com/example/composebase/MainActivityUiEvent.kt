package com.example.composebase

sealed class MainActivityUiEvent {
    data object OnStart : MainActivityUiEvent()
}
