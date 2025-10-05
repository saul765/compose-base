package com.example.composebase.presentation

import androidx.compose.material3.SnackbarDuration
import androidx.lifecycle.viewModelScope
import com.example.composebase.core.base.viewmodel.BaseViewModel
import com.example.composebase.core.helpers.UiText
import com.example.composebase.core.utils.events.UiEvent
import com.example.composebase.core.utils.network.INetworkMonitor
import kotlinx.coroutines.launch

class MainViewModel(private val networkMonitor: INetworkMonitor) : BaseViewModel() {
    private fun onStart() = viewModelScope.launch {
        networkMonitor.isOnline.collect {
            if (!it) {
                sendEvent(
                    UiEvent.ShowSnackBar(
                        message = UiText.StringResource(_root_ide_package_.com.example.composebase.R.string.offline_mode_message),
                        duration = SnackbarDuration.Long
                    )
                )
            }
        }
    }

    fun onEvent(event: MainActivityUiEvent) {
        when (event) {
            is MainActivityUiEvent.OnStart -> onStart()
        }
    }
}
