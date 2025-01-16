package com.example.composebase.core.base.screen


import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.composebase.core.EMPTY_CHARACTER
import com.example.composebase.core.base.SearchToolbar
import com.example.composebase.core.base.viewmodel.BaseSearchViewModel
import com.example.composebase.core.data.UIStateStatus

@Composable
inline fun <reified VM : BaseSearchViewModel<S>, S> BaseSearchScreen(
    crossinline successView: @Composable (S) -> Unit,
    noinline onBack: () -> Unit = {},
) {
    val topBarState = rememberSearchTextFieldState(EMPTY_CHARACTER)

    BaseScreen<VM>(
        topBar = {
            SearchToolbar(
                onBackClick = onBack,
                searchState = topBarState
            )
        },
    ) { viewModel ->

        val state by viewModel.uiState.collectAsStateWithLifecycle()

        LaunchedEffect(topBarState) {
            snapshotFlow { topBarState.text }
                .collect { viewModel.onSearchQueryChanged(it) }
        }

        when (val uiState = state) {
            is UIStateStatus.Success -> {
                successView(
                    uiState.data
                )
            }

            else -> Unit
        }
    }
}