package com.example.composebase.core.utils.extensions


import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.composebase.core.base.state.BaseDialogState
import com.example.composebase.core.data.UIStateStatus
import com.example.composebase.core.data.exception.ErrorMessage
import com.example.composebase.core.data.exception.getErrorMessage
import com.example.composebase.core.design_system.ErrorDialogInformation


@Composable
fun UIStateStatus.Error<*>.ShowErrorDialog() {
    val error = exception.getErrorMessage()
    val failureReason = when (val second = error.second) {
        is ErrorMessage.Resource -> Pair(
            stringResource(error.first),
            stringResource(second.resId)
        )

        is ErrorMessage.Text -> Pair(
            stringResource(error.first),
            second.text
        )
    }

    ErrorDialogInformation(
        dialogText = failureReason.first,
        dialogAssistText = failureReason.second
    )
}

@Composable
fun Throwable.ShowErrorDialog(dialogState: BaseDialogState, onDismissRequest: () -> Unit = {}) {
    val error = getErrorMessage()
    val failureReason = when (val second = error.second) {
        is ErrorMessage.Resource -> Pair(
            stringResource(error.first),
            stringResource(second.resId)
        )

        is ErrorMessage.Text -> Pair(
            stringResource(error.first),
            second.text
        )
    }

    ErrorDialogInformation(
        state = dialogState,
        onDismissRequest = onDismissRequest,
        dialogText = failureReason.first,
        dialogAssistText = failureReason.second
    )
}