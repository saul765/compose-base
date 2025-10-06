package com.example.composebase.presentation.feature.contries.detail

import com.example.composebase.domain.model.Country

sealed class CountryDetailUiEvent {
    data class OnStart(val country: Country) : CountryDetailUiEvent()
    data class OnBackClick(val country: Country) : CountryDetailUiEvent()
}
