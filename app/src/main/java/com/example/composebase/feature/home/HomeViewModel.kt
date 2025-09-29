package com.example.composebase.feature.home

import com.example.composebase.core.base.viewmodel.BaseViewModel
import com.example.composebase.core.utils.events.UiEvent

class HomeViewModel : BaseViewModel() {

    fun onEvent(event: HomeUiEvent) {
        when (event) {
            is HomeUiEvent.OnStart -> sendEvent(UiEvent.Loading(isLoading = true))
            HomeUiEvent.OnRefresh -> sendEvent(UiEvent.Loading(isLoading = false))
        }
    }
}