package com.example.composebase.core.helpers

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

sealed class UiText {
    data class DynamicString(val text: String) : UiText()
    data class StringResource(val resId: Int) : UiText()

    fun asString(context: Context): String = when (this) {
        is DynamicString -> text
        is StringResource -> context.getString(resId)
    }

    @Composable
    fun asString(): String = when (this) {
        is DynamicString -> text
        is StringResource -> stringResource(resId)
    }
}
