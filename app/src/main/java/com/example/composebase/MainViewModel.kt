package com.example.composebase

import androidx.compose.material3.SnackbarDuration
import androidx.lifecycle.viewModelScope
import com.example.composebase.core.base.viewmodel.BaseViewModel
import com.example.composebase.core.helpers.UiText
import com.example.composebase.core.model.AppUiState
import com.example.composebase.core.network.INetworkMonitor
import com.example.composebase.core.utils.events.UiEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class MainViewModel(private val networkMonitor: INetworkMonitor) : BaseViewModel() {

    init {
        onStart()
    }

    private fun onStart() = viewModelScope.launch {
        networkMonitor.isOnline
            .distinctUntilChanged()
            .collect { isOnline ->
                if (!isOnline) {
                    sendEvent(
                        UiEvent.ShowSnackBar(
                            message = UiText.StringResource(R.string.offline_mode_message),
                            duration = SnackbarDuration.Long
                        )
                    )
                }
            }
    }
}
