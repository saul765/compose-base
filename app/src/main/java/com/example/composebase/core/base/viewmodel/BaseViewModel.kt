package com.example.composebase.core.base.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composebase.core.utils.bus.UiEventBus
import com.example.composebase.core.utils.events.UiEvent
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


abstract class BaseViewModel : ViewModel(), KoinComponent {
    protected val _uiEvents by inject<UiEventBus>()
    val uiEvents = _uiEvents.getEvents()

    fun sendEvent(event: UiEvent) = viewModelScope.launch {
        _uiEvents.sendEvent(event)
    }
}