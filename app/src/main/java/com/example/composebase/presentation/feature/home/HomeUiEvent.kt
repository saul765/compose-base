package com.example.composebase.presentation.feature.home

import com.example.composebase.domain.model.Country

sealed class HomeUiEvent {
    data object OnStart : HomeUiEvent()
    data class OnCountryClick(val country: Country) : HomeUiEvent()

}
