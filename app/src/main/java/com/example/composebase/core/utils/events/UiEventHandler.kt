package com.example.composebase.core.utils.events

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.composebase.core.design_system.SimpleLoader
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

@Composable
fun UiEventHandler(
    events: Flow<UiEvent>,
    content: @Composable () -> Unit
) {
    var isLoading by rememberSaveable { mutableStateOf(false) }
    var snackBarHostState by remember { mutableStateOf(SnackbarHostState()) }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    ObserveFlowEvents(events) { event ->
        when (event) {
            is UiEvent.ShowSnackBar -> {
                scope.launch {
                    snackBarHostState.showSnackbar(message = event.message.asString(context = context))
                }
            }

            is UiEvent.Loading -> {
                isLoading = event.isLoading
            }

            is UiEvent.ShowToast -> {
                Toast.makeText(
                    context,
                    event.message.asString(context = context),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
    BackHandler(enabled = isLoading) { }

    Box(modifier = Modifier.fillMaxSize()) {
        CompositionLocalProvider(LocalSnackBarState provides snackBarHostState) {
            content()
        }
        AnimatedVisibility(
            visible = isLoading,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            SimpleLoader(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.surface)
            )
        }
    }
}

val LocalSnackBarState = compositionLocalOf { SnackbarHostState() }