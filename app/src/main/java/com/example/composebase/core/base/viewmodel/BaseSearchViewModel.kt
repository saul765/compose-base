package com.example.composebase.core.base.viewmodel

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class BaseSearchViewModel<S> : BaseViewModel() {

    abstract val searchQuery: MutableStateFlow<String>

    abstract val uiState: StateFlow<S>

    abstract fun onSearchQueryChanged(query: String)

}
