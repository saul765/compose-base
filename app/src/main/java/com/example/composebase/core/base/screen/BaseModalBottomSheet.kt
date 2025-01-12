package com.example.composebase.core.base.screen

import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.IntSize
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.composebase.core.EMPTY_CHARACTER
import com.example.composebase.core.base.BaseLoading
import com.example.composebase.core.base.state.BaseDialogState
import com.example.composebase.core.base.viewmodel.BaseViewModel
import com.example.composebase.core.design_system.BottomSheet
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel



@OptIn(ExperimentalMaterial3Api::class)
@Composable
inline fun <reified VM : BaseViewModel> BaseModalBottomSheet(
    modifier: Modifier = Modifier,
    viewModel: VM = koinViewModel(),
    sheetState: SheetState = rememberModalBottomSheetState(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    bottomSheetUiState: BaseDialogState = rememberBaseBottomSheetState(),
    sizeState: IntSize = IntSize.Zero,
    crossinline content: @Composable (VM) -> Unit
) {
    val localDensity = LocalDensity.current

    val loadingState by viewModel.loadingState.collectAsStateWithLifecycle()

    if (bottomSheetUiState.isDialogVisible) {
        BottomSheet(
            sheetState = sheetState,
            modifier = modifier,
            onDismissRequest = {
                coroutineScope.launch {
                    sheetState.hide()
                }.invokeOnCompletion { bottomSheetUiState.closeDialog() }
            }
        ) {
            content(viewModel)
            loadingState.isLoading.takeIf { it }?.let {
                BaseLoading(
                    modifier = with(localDensity) {
                        Modifier.size(
                            sizeState.width.toDp(),
                            sizeState.height.toDp()
                        )
                    },
                    message = loadingState.message?.let { msg -> stringResource(msg) }
                        ?: EMPTY_CHARACTER
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseModalBottomSheet(
    modifier: Modifier = Modifier,
    sheetState: SheetState = rememberModalBottomSheetState(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    bottomSheetUiState: BaseDialogState = rememberBaseBottomSheetState(),
    backgroundColor: Color = Color.White,
    content: @Composable () -> Unit
) {
    if (bottomSheetUiState.isDialogVisible) {
        ModalBottomSheet(
            sheetState = sheetState,
            containerColor = backgroundColor,
            modifier = modifier,
            onDismissRequest = {
                coroutineScope.launch {
                    sheetState.hide()
                }.invokeOnCompletion { bottomSheetUiState.closeDialog() }
            }
        ) {
            content()
        }
    }
}

@Composable
fun rememberBaseBottomSheetState(): BaseDialogState =
    remember {
        BaseDialogState(false)
    }
