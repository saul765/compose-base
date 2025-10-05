package com.example.composebase.core.utils.bus

import com.example.composebase.core.utils.events.UiEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import org.koin.core.component.KoinComponent

class UiEventBus() : IUiEventBus, KoinComponent {
    private val _events = Channel<UiEvent>()

    override fun getEvents(): Flow<UiEvent> = _events.receiveAsFlow()

    override suspend fun sendEvent(event: UiEvent) = _events.send(event)

    override fun trySendEvent(event: UiEvent) {
        _events.trySend(event)
    }
}
