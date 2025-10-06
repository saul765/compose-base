package com.example.composebase.core.utils.bus

import com.example.composebase.core.utils.events.UiEvent
import kotlinx.coroutines.flow.Flow

interface IUiEventBus {
    fun getEvents(): Flow<UiEvent>
    fun sendEvent(event: UiEvent)
}