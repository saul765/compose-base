package com.example.composebase.core.design_system

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.res.stringResource
import com.example.composebase.R
import com.example.composebase.core.base.state.BaseDialogState
import com.example.composebase.core.base.state.rememberDialogState
import com.example.composebase.core.design_system.icon.BaseIcons
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.MultiplePermissionsState
import com.google.accompanist.permissions.rememberMultiplePermissionsState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun BasePermission(
    dialogState: BaseDialogState = rememberDialogState(),
    state: MultiplePermissionsState = rememberMultiplePermissionsState(listOf()),
    rationale: String,
    goToAppSettings: () -> Unit,
    onGranted: () -> Unit,
    mustRequire: Boolean = false
) {
    LaunchedEffect(state.allPermissionsGranted) {
        if (state.allPermissionsGranted) {
            onGranted.invoke()
        }
    }
    if (!state.allPermissionsGranted) {
        if (state.shouldShowRationale) {
            AlertDialogInformation(
                state = dialogState,
                onDismissRequest = {
                },
                onConfirmation = {
                    goToAppSettings()
                },
                dialogTitle = stringResource(R.string.alert_dialog_permissions_title),
                dialogText = rationale,
                icon = BaseIcons.Info,
                confirmText = stringResource(R.string.alert_dialog_permissions_rationale_settings)
            )
        } else {
            if (mustRequire) {
                AlertDialogInformation(
                    state = dialogState,
                    onDismissRequest = {
                    },
                    onConfirmation = {
                        goToAppSettings()
                    },
                    dialogTitle = stringResource(R.string.alert_dialog_permissions_title),
                    dialogText = rationale,
                    icon = BaseIcons.Info,
                    confirmText = stringResource(R.string.alert_dialog_permissions_rationale_settings)
                )
            }
        }
    }
}