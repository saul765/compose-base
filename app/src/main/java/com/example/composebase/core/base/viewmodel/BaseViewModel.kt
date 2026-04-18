package com.example.composebase.core.base.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composebase.core.utils.events.UiEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent


abstract class BaseViewModel : ViewModel(), KoinComponent {

    private companion object {
        const val STATE_IN_STOP_TIMEOUT_MILLIS = 5_000L
    }

    private val uiEventChannel = Channel<UiEvent>()

    val uiEvents = uiEventChannel.receiveAsFlow()

    protected fun <T> Flow<T>.stateInViewModel(
        initialState: T,
        onCollectionStart: () -> Unit = {}
    ): StateFlow<T> = onStart { onCollectionStart() }
        .stateIn(
            scope = viewModelScope,
            initialValue = initialState,
            started = SharingStarted.WhileSubscribed(STATE_IN_STOP_TIMEOUT_MILLIS)
        )

    protected fun <T> MutableStateFlow<T>.stateInViewModel(
        onCollectionStart: () -> Unit = {}
    ): StateFlow<T> = this
        .onStart { onCollectionStart() }
        .stateIn(
            scope = viewModelScope,
            initialValue = value,
            started = SharingStarted.WhileSubscribed(STATE_IN_STOP_TIMEOUT_MILLIS)
        )

    protected fun sendEvent(event: UiEvent) = viewModelScope.launch {
        uiEventChannel.send(event)
    }
}
