package com.example.composebase.core.utils.extensions


import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.composebase.core.data.exception.ErrorMessage
import com.example.composebase.core.data.exception.getErrorMessage
import com.example.composebase.core.design_system.ErrorDialogInformation

@Composable
fun Throwable.ShowErrorDialog(isVisible: Boolean = true, onDismissRequest: () -> Unit = {}) {
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
        isVisible = isVisible,
        onDismissRequest = onDismissRequest,
        dialogText = failureReason.first,
        dialogAssistText = failureReason.second
    )
}
