package com.example.composebase.core.base.viewmodel

import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import com.example.composebase.R
import com.example.composebase.core.model.LoadingState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.koin.core.component.KoinComponent


abstract class BaseViewModel : ViewModel(), KoinComponent {

    private val _loadingState = MutableStateFlow(LoadingState())

    val loadingState = _loadingState.asStateFlow()

    private val _baseErrorState = MutableStateFlow<Throwable?>(null)

    open val baseErrorState = _baseErrorState.asStateFlow()

    open fun showError(throwable: Throwable) {
        _baseErrorState.update { throwable }
    }

    open fun hideError() {
        _baseErrorState.update { null }
    }

    open fun showLoading(@StringRes message: Int = R.string.local_default_loading_message) {
        _loadingState.update {
            it.copy(isLoading = true, message = message)
        }
    }

    open fun hideLoading() {
        _loadingState.update { it.copy(isLoading = false) }
    }
}