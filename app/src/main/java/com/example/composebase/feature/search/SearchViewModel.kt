package com.example.composebase.feature.search

import androidx.lifecycle.viewModelScope
import com.example.composebase.core.EMPTY_CHARACTER
import com.example.composebase.core.base.viewmodel.BaseSearchViewModel
import com.example.composebase.core.data.UIStateStatus
import com.example.composebase.core.database.entity.toUIModel
import com.example.composebase.core.usecases.IGetPokemonUseCase
import com.example.composebase.feature.home.state.HomeUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class SearchViewModel(
    getPokemonLocal: IGetPokemonUseCase
) : BaseSearchViewModel<HomeUiState>() {

    override val searchQuery: MutableStateFlow<String> = MutableStateFlow(EMPTY_CHARACTER)

    override val uiState = combine(getPokemonLocal.execute(), searchQuery)
    { pokemons, query ->

        val finalPokemons = pokemons
            .map { it.toUIModel() }
            .filter {
                it.name.contains(query, ignoreCase = true) ||
                        (it.name.contains(query, ignoreCase = true).not() && it.id.toString()
                            .contains(query, ignoreCase = true))
            }

        UIStateStatus.Success(HomeUiState(pokemons = finalPokemons))

    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = UIStateStatus.Empty(HomeUiState()),
    )

    override fun onSearchQueryChanged(query: String) {
        searchQuery.update { query }
    }
}