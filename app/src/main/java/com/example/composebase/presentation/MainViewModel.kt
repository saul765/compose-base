package com.example.composebase.presentation

import androidx.compose.material3.SnackbarDuration
import com.example.composebase.R
import com.example.composebase.core.base.viewmodel.BaseViewModel
import com.example.composebase.core.helpers.UiText
import com.example.composebase.core.utils.events.UiEvent
import com.example.composebase.core.utils.network.INetworkMonitor
import kotlinx.coroutines.flow.distinctUntilChanged

class MainViewModel(private val networkMonitor: INetworkMonitor) : BaseViewModel() {
    private fun onStart() = dispatch {
        networkMonitor.isOnline.distinctUntilChanged().collect {
            if (!it) {
                sendEvent(
                    UiEvent.ShowSnackBar(
                        message = UiText.StringResource(R.string.offline_mode_message),
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
