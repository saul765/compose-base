package com.example.composebase.core.base.screen


import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.composebase.core.EMPTY_CHARACTER

@Stable
class SearchTextFieldState(private val hint: String, initialText: String = EMPTY_CHARACTER) {
    var text by mutableStateOf(initialText)
        private set

    fun updateText(newText: String) {
        text = newText
    }

    val isHint: Boolean
        get() = text == hint
}

@Composable
fun rememberSearchTextFieldState(hint: String): SearchTextFieldState =
    remember(hint) {
        SearchTextFieldState(hint, hint)
    }