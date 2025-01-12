package com.example.composebase

import androidx.lifecycle.viewModelScope
import com.example.composebase.core.base.viewmodel.BaseViewModel
import com.example.composebase.core.model.AppUiState
import com.example.composebase.core.network.INetworkMonitor
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn

class MainViewModel(networkMonitor: INetworkMonitor) : BaseViewModel() {


    val uiState: StateFlow<AppUiState> = combine(
        networkMonitor.isOnline,
        flow<Unit> { } //update with any other state you want to combine
    ) { isOnline, _ ->
        AppUiState(isOnline = isOnline)
    }.stateIn(
        scope = viewModelScope,
        initialValue = AppUiState(),
        started = SharingStarted.WhileSubscribed(5_000)
    )


}