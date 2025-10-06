package com.example.composebase.presentation.feature.home

import com.example.composebase.core.base.viewmodel.BaseViewModel
import com.example.composebase.core.helpers.UiText
import com.example.composebase.core.utils.events.UiEvent
import com.example.composebase.domain.model.Country
import com.example.composebase.domain.usecases.IGetCountriesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class HomeViewModel(private val getCountriesUseCase: IGetCountriesUseCase) : BaseViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()
    var hasData = false


    private fun onStart() {
        if (hasData) return

        dispatch {
            val countries = getCountriesUseCase()
            _uiState.update { it.copy(countries = countries) }
        }
    }

    private fun onCountryClick(country: Country) {
        sendEvent(
            UiEvent.ShowToast(UiText.DynamicString(country.capital))
        )
    }

    fun onEvent(event: HomeUiEvent) {
        when (event) {
            is HomeUiEvent.OnStart -> onStart()
            is HomeUiEvent.OnCountryClick -> onCountryClick(event.country)
        }
    }
}
