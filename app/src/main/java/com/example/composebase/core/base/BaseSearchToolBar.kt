package com.example.composebase.core.base

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.composebase.core.EMPTY_CHARACTER
import com.example.composebase.core.base.screen.SearchTextFieldState
import com.example.composebase.core.design_system.OutlinedTextFieldWithIcons
import com.example.composebase.core.design_system.icon.BaseIcons


@Composable
fun SearchToolbar(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    searchState: SearchTextFieldState = SearchTextFieldState(EMPTY_CHARACTER),
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth(),
    ) {
        IconButton(onClick = onBackClick) {
            Icon(
                imageVector = BaseIcons.ArrowBack,
                contentDescription = "Back",
            )
        }
        SearchTextField(state = searchState)
    }
}


@Composable
private fun SearchTextField(
    state: SearchTextFieldState = SearchTextFieldState(EMPTY_CHARACTER),
) {
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current

    val onSearchExplicitlyTriggered = {
        keyboardController?.hide()
    }

    OutlinedTextFieldWithIcons(
        leadingIcon = {
            Icon(
                imageVector = BaseIcons.Search,
                contentDescription = "Buscar",
            )
        },
        trailingIcon = {
            if (state.text.isNotEmpty()) {
                IconButton(
                    onClick = {
                        state.updateText(EMPTY_CHARACTER)
                    },
                ) {
                    Icon(
                        imageVector = BaseIcons.Close,
                        contentDescription = "Borrar",
                    )
                }
            }
        },
        onValueChange = {
            if (!it.contains("\n")) {
                state.updateText(it)
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .focusRequester(focusRequester)
            .onKeyEvent {
                it
                    .takeIf { it.key == Key.Enter }
                    ?.let {
                        onSearchExplicitlyTriggered()
                        true
                    } ?: false
            }
            .testTag("searchTextField"),
        shape = RoundedCornerShape(32.dp),
        value = state.text,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search,
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                onSearchExplicitlyTriggered()
            },
        ),
        maxLines = 1,
        singleLine = true,
    )

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
}
