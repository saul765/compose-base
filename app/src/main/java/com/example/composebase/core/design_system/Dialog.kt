package com.example.composebase.core.design_system


import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.example.composebase.R
import com.example.composebase.core.base.state.BaseDialogState
import com.example.composebase.core.base.state.rememberDialogState


@Composable
fun GAFAlertDialog(
    modifier: Modifier = Modifier,
    state: BaseDialogState = rememberDialogState(),
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogText: String,
    icon: ImageVector,
    confirmText: String,
    dismissText: String
) = GAFAlertDialog(
    modifier = modifier,
    state = state,
    onDismissRequest = onDismissRequest,
    onConfirmation = onConfirmation,
    dialogTitle = dialogTitle,
    dialogText = {
        Text(text = dialogText)
    },
    icon = icon,
    confirmText = confirmText,
    dismissText = dismissText
)


@Composable
fun GAFAlertDialog(
    modifier: Modifier = Modifier,
    state: BaseDialogState = rememberDialogState(),
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogText: @Composable () -> Unit,
    icon: ImageVector,
    confirmText: String,
    dismissText: String
) {


    if (state.isDialogVisible) {
        MaterialTheme() {
            AlertDialog(
                modifier = modifier,
                icon = {
                    Icon(
                        icon,
                        contentDescription = stringResource(R.string.icon_content_description_text)
                    )
                },
                title = {
                    Text(text = dialogTitle)
                },
                text = dialogText,
                onDismissRequest = {
                    onDismissRequest()
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            onConfirmation()
                        }
                    ) {
                        Text(confirmText)
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = {
                            onDismissRequest()
                        }
                    ) {
                        Text(dismissText)
                    }
                }
            )
        }
    }
}

@Composable
fun GAFContentAlertDialog(
    modifier: Modifier = Modifier,
    state: BaseDialogState = rememberDialogState(),
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogText: String,
    icon: ImageVector,
    confirmText: String,
    dismissText: String,
    content: @Composable () -> Unit
) {

    if (state.isDialogVisible) {
        MaterialTheme() {
            AlertDialog(
                modifier = modifier,
                icon = {
                    Icon(
                        icon,
                        contentDescription = stringResource(R.string.icon_content_description_text)
                    )
                },
                title = {
                    Text(text = dialogTitle)
                },
                text = {
                    Column {
                        Text(text = dialogText)
                        content()
                    }
                },
                onDismissRequest = {
                    onDismissRequest().also { state.closeDialog() }
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            onConfirmation()
                        }
                    ) {
                        Text(confirmText)
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = {
                            onDismissRequest().also { state.closeDialog() }
                        }
                    ) {
                        Text(dismissText)
                    }
                }
            )
        }
    }
}

@Composable
fun GAFAlertDialogInformationWithAnimatedIcon(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogText: String,
    dialogAssistText: String? = null,
    icon: @Composable (() -> Unit)? = null,
    confirmText: String,
) {

    MaterialTheme() {
        AlertDialog(
            modifier = modifier,
            icon = icon,
            title = {
                Text(text = dialogTitle)
            },
            text = {
                Column {
                    Text(text = dialogText)
                    dialogAssistText?.let {
                        Text(text = dialogAssistText)
                    }
                }
            },
            onDismissRequest = {
                onDismissRequest()
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        onConfirmation()
                    }
                ) {
                    Text(confirmText)
                }
            },
        )
    }
}

@Composable
fun GAFErrorDialogInformation(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit = {},
    onConfirmation: () -> Unit = {},
    @StringRes dialogTitle: Int = R.string.alert_dialog_error_title,
    dialogText: String,
    dialogAssistText: String? = null,
    icon: ImageVector = Icons.Default.Cancel,
    @StringRes confirmText: Int = R.string.alert_dialog_accept,
    state: BaseDialogState = rememberDialogState()
) {

    GAFAlertDialogInformation(
        state = state,
        modifier = modifier,
        onDismissRequest = {
            onDismissRequest().also { state.closeDialog() }
        },
        onConfirmation = {
            onConfirmation()
        },
        dialogTitle = stringResource(dialogTitle),
        dialogText = dialogText,
        dialogAssistText = dialogAssistText,
        icon = icon,
        confirmText = stringResource(confirmText)
    )

}


@Composable
fun GAFAlertDialogInformation(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit = {},
    onConfirmation: () -> Unit = {},
    dialogTitle: String,
    dialogText: String,
    dialogAssistText: String? = null,
    icon: ImageVector = Icons.Default.Cancel,
    confirmText: String,
    state: BaseDialogState = rememberDialogState()
) = GAFAlertDialogInformation(
    state = state,
    dialogAssistText = dialogAssistText,
    dialogTitle = dialogTitle,
    dialogText = {
        Text(text = dialogText)
    },
    onDismissRequest = onDismissRequest,
    onConfirmation = onConfirmation,
    icon = icon,
    confirmText = confirmText,
)


@Composable
fun GAFAlertDialogInformation(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit = {},
    onConfirmation: () -> Unit = {},
    dialogTitle: String,
    dialogText: @Composable () -> Unit,
    dialogAssistText: String? = null,
    icon: ImageVector = Icons.Default.Cancel,
    confirmText: String,
    state: BaseDialogState = rememberDialogState()
) {


    if (state.isDialogVisible) {
        MaterialTheme() {
            AlertDialog(
                modifier = modifier,
                icon = {
                    Icon(
                        icon,
                        contentDescription = stringResource(R.string.icon_content_description_text)
                    )
                },
                title = {
                    Text(text = dialogTitle)
                },
                text = {
                    Column {
                        dialogText()
                        dialogAssistText?.let {
                            Text(text = dialogAssistText)
                        }
                    }
                },
                onDismissRequest = {
                    onDismissRequest().also { state.closeDialog() }
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            onConfirmation().also { state.closeDialog() }
                        }
                    ) {
                        Text(confirmText)
                    }
                }
            )
        }
    }
}
