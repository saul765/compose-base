package com.example.composebase.core.base.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composebase.R
import com.example.composebase.core.helpers.UiText
import com.example.composebase.core.utils.bus.UiEventBus
import com.example.composebase.core.utils.coroutines.ICoroutineContextProvider
import com.example.composebase.core.utils.events.UiEvent
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel : ViewModel(), KoinComponent {

    protected val dispatchersProvider by inject<ICoroutineContextProvider>()
    protected val _uiEvents by inject<UiEventBus>()
    val uiEvents = _uiEvents.getEvents()

    protected suspend fun sendEvent(event: UiEvent) = _uiEvents.sendEvent(event)

    protected fun trySendEvent(event: UiEvent) = _uiEvents.trySendEvent(event)

    protected fun dispatch(
        dispatcher: CoroutineContext = dispatchersProvider.getIOContext(),
        block: suspend () -> Unit
    ) = viewModelScope.launch(dispatcher + handler) { block() }


    protected fun executeUseCases(
        dispatcher: CoroutineContext = dispatchersProvider.getIOContext(),
        block: suspend () -> Unit,
    ) = dispatch(dispatcher) {
        sendEvent(UiEvent.Loading(true))
        block()
        sendEvent(UiEvent.Loading(false))
    }

    private val handler = CoroutineExceptionHandler { _, _ ->
        _uiEvents.trySendEvent(
            UiEvent.ShowSnackBar(
                UiText.StringResource(R.string.local_unexpected_error_message)
            )
        )
    }
}
