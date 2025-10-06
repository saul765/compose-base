package com.example.composebase.core.utils.events

import androidx.compose.material3.SnackbarDuration
import androidx.navigation.NavOptions
import com.example.composebase.core.helpers.UiText

sealed interface UiEvent {
    data class ShowSnackBar(
        val message: UiText,
        val duration: SnackbarDuration = SnackbarDuration.Short
    ) : UiEvent

    data class Loading(val isLoading: Boolean) : UiEvent

    data class ShowToast(val message: UiText) : UiEvent

    sealed interface Navigation : UiEvent {
        object NavigateBack : Navigation
        data class NavigateTo(val route: Any, val navOptions: NavOptions? = null) : Navigation
        data class PopUpTo(val route: Any, val inclusive: Boolean = false) : Navigation
    }
}
