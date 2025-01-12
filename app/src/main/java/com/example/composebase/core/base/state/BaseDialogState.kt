package com.example.composebase.core.base.state

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

open class BaseDialogState(
    isDialogVisible: Boolean = false
) {

    var isDialogVisible by mutableStateOf(isDialogVisible)
        private set

    fun openDialog() {
        isDialogVisible = true
    }

    fun closeDialog() {
        isDialogVisible = false
    }
}

@Composable
fun rememberDialogState(
    isDialogVisible: Boolean = false
): BaseDialogState = remember(isDialogVisible) { BaseDialogState(isDialogVisible) }
