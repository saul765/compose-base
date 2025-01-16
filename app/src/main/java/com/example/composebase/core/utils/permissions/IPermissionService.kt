package com.example.composebase.core.utils.permissions

import androidx.fragment.app.Fragment

interface IPermissionService {

    fun runWithPermission(
        context: Fragment,
        permission: String,
        onRationale: () -> Unit,
        onGranted: () -> Unit,
        onDenied: () -> Unit
    )

    fun runWithPermission(
        context: Fragment,
        permissionsList: List<String>,
        onRationale: () -> Unit,
        onGranted: () -> Unit,
        onDenied: () -> Unit
    )
}