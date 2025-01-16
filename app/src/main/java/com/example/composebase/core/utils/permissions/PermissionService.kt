package com.example.composebase.core.utils.permissions

import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment

class PermissionService : IPermissionService {
    override fun runWithPermission(
        context: Fragment,
        permission: String,
        onRationale: () -> Unit,
        onGranted: () -> Unit,
        onDenied: () -> Unit
    ) {
        val permissionLauncher =
            context.registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
                when {
                    isGranted -> onGranted()
                    context.shouldShowRequestPermissionRationale(permission) -> onRationale()
                    else -> onDenied()
                }
            }
        permissionLauncher.launch(permission)
    }

    override fun runWithPermission(
        context: Fragment,
        permissionsList: List<String>,
        onRationale: () -> Unit,
        onGranted: () -> Unit,
        onDenied: () -> Unit
    ) {
        val permissionLauncher =
            context.registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
                val isGranted = permissions.values.all { it }
                when {
                    isGranted -> onGranted()
                    permissions.any { !it.value && context.shouldShowRequestPermissionRationale(it.key) } -> onRationale()
                    else -> onDenied()
                }
            }
        permissionLauncher.launch(permissionsList.toTypedArray())
    }

}