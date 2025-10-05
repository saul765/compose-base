package com.example.composebase.core.utils.events

import androidx.compose.material3.SnackbarDuration
import com.example.composebase.core.helpers.UiText

sealed interface UiEvent {
    data class ShowSnackBar(
        val message: UiText,
        val duration: SnackbarDuration = SnackbarDuration.Short
    ) : UiEvent

    data class Loading(val isLoading: Boolean) : UiEvent
}
