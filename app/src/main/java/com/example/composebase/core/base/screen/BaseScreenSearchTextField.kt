package com.example.composebase.core.base.screen


import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.composebase.core.EMPTY_CHARACTER
import com.example.composebase.core.base.SearchToolbar
import com.example.composebase.core.base.viewmodel.BaseSearchViewModel
import com.example.composebase.core.compose.AppScaffold
import org.koin.androidx.compose.koinViewModel

@Composable
inline fun <reified VM : BaseSearchViewModel<S>, S> BaseSearchScreen(
    crossinline successView: @Composable (S) -> Unit,
    noinline onBack: () -> Unit = {},
    viewModel: VM = koinViewModel()
) {
    val topBarState = rememberSearchTextFieldState(EMPTY_CHARACTER)

    AppScaffold(
        topBar = {
            SearchToolbar(
                onBackClick = onBack,
                searchState = topBarState
            )
        }
    ) {

        val state by viewModel.uiState.collectAsStateWithLifecycle()

        LaunchedEffect(topBarState.text) {
            viewModel.onSearchQueryChanged(topBarState.text)
        }

        successView(state)
    }
}
