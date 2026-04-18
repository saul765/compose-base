package com.example.composebase.feature.search

import com.example.composebase.core.EMPTY_CHARACTER
import com.example.composebase.core.base.viewmodel.BaseSearchViewModel
import com.example.composebase.core.database.entity.toUIModel
import com.example.composebase.core.usecases.IGetPokemonUseCase
import com.example.composebase.feature.home.HomeUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.collections.immutable.toImmutableList

class SearchViewModel(
    getPokemonLocal: IGetPokemonUseCase
) : BaseSearchViewModel<HomeUiState>() {

    override val searchQuery: MutableStateFlow<String> = MutableStateFlow(EMPTY_CHARACTER)

    override val uiState: StateFlow<HomeUiState> = combine(
        getPokemonLocal.execute(),
        searchQuery
    ) { pokemons, query ->

        val finalPokemons = pokemons
            .map { it.toUIModel() }
            .filter {
                it.name.contains(query, ignoreCase = true) ||
                    (!it.name.contains(query, ignoreCase = true) &&
                        it.id.toString().contains(query, ignoreCase = true))
            }.toImmutableList()

        HomeUiState(
            pokemons = finalPokemons,
            notificationCount = finalPokemons.size
        )
    }.stateInViewModel(HomeUiState())

    override fun onSearchQueryChanged(query: String) {
        searchQuery.update { query }
    }
}
