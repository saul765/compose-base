package com.example.composebase.core.design_system

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun BasePermission(
    permissions: List<String>,
    onGranted: () -> Unit,
    onShouldShowRationale: (requestPermission: () -> Unit) -> Unit = {},
    onPermanentlyDenied: () -> Unit = {}
) {
    val currentOnGranted by rememberUpdatedState(onGranted)
    val currentOnShouldShowRationale by rememberUpdatedState(onShouldShowRationale)
    val currentOnPermanentlyDenied by rememberUpdatedState(onPermanentlyDenied)
    var permissionResultVersion by rememberSaveable(permissions) { mutableIntStateOf(0) }
    val permissionState = rememberMultiplePermissionsState(permissions) {
        permissionResultVersion += 1
    }
    var hasRequested by rememberSaveable(permissions) { mutableStateOf(false) }
    var hasDispatchedGranted by rememberSaveable(permissions) { mutableStateOf(false) }
    val requestPermission = {
        hasRequested = true
        permissionState.launchMultiplePermissionRequest()
    }

    LaunchedEffect(permissionState.allPermissionsGranted) {
        if (permissionState.allPermissionsGranted && !hasDispatchedGranted) {
            hasDispatchedGranted = true
            currentOnGranted()
        } else if (!permissionState.allPermissionsGranted) {
            hasDispatchedGranted = false
        }
    }

    LaunchedEffect(permissionState.allPermissionsGranted, hasRequested) {
        if (!permissionState.allPermissionsGranted && !hasRequested) {
            requestPermission()
        }
    }

    LaunchedEffect(permissionResultVersion) {
        if (permissionResultVersion == 0 || permissionState.allPermissionsGranted) {
            return@LaunchedEffect
        }
        if (permissionState.shouldShowRationale) {
            currentOnShouldShowRationale(requestPermission)
        } else {
            currentOnPermanentlyDenied()
        }
    }
}
