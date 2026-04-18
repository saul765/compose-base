package com.example.composebase.feature.home

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.composebase.core.base.viewmodel.BaseViewModel
import com.example.composebase.core.usecases.IGetPagedPokemonUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class HomeViewModel(
    private val getPagedPokemonUseCase: IGetPagedPokemonUseCase
) : BaseViewModel() {

    val uiState: StateFlow<HomeUiState>
        field = MutableStateFlow(HomeUiState())

    private fun onPermissionGranted() {
        uiState.update {
            it.copy(
                pokemons = getPagedPokemonUseCase
                    .execute()
                    .cachedIn(viewModelScope)
            )
        }
    }

    fun onEvent(event: HomeScreenUiEvent) {
        when (event) {
            HomeScreenUiEvent.OnPermissionGranted -> onPermissionGranted()
        }
    }
}
