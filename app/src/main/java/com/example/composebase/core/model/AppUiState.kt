package com.example.composebase.core.model

import com.example.composebase.core.utils.events.UiEvent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class AppUiState(
    val uiEvents: Flow<UiEvent> = emptyFlow()
)
