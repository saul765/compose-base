package com.example.composebase.core.base.screen


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.composebase.core.EMPTY_CHARACTER
import com.example.composebase.core.base.BaseLoading
import com.example.composebase.core.base.state.rememberDialogState
import com.example.composebase.core.base.viewmodel.BaseViewModel
import com.example.composebase.core.utils.extensions.ShowErrorDialog
import kotlinx.coroutines.flow.filter
import org.koin.androidx.compose.koinViewModel


@Composable
inline fun <reified V : BaseViewModel> BaseScreen(
    modifier: Modifier = Modifier,
    viewModel: V = koinViewModel(),
    noinline topBar: @Composable () -> Unit = {},
    noinline floatingActionButton: @Composable () -> Unit = {},
    noinline snackBarHost: @Composable () -> Unit = {},
    crossinline content: @Composable (V) -> Unit
) {
    val loadingState by viewModel.loadingState.collectAsStateWithLifecycle()

    val dialogState = rememberDialogState(false)

    val baseErrorState by viewModel.baseErrorState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        snapshotFlow { baseErrorState }
            .filter { it != null }
            .collect {
                dialogState.openDialog()
            }
    }

    Scaffold(
        modifier = modifier,
        topBar = topBar,
        floatingActionButton = floatingActionButton,
        snackbarHost = snackBarHost,
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .consumeWindowInsets(paddingValues)
                    .padding(paddingValues)
            ) {
                content(viewModel)
                loadingState.isLoading.takeIf { it }?.let {
                    BaseLoading(message = loadingState.message?.let { stringResource(it) }
                        ?: EMPTY_CHARACTER)
                }

                baseErrorState?.ShowErrorDialog(
                    dialogState = dialogState,
                    onDismissRequest = { viewModel.hideError() }
                )
            }
        }
    )
}