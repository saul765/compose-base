package com.example.composebase.core.usecases

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.composebase.core.database.entity.toUIModel
import com.example.composebase.core.model.uiModel.PokemonItemUIModel
import com.example.composebase.core.paging.PokemonRemoteMediator
import com.example.composebase.core.repositories.pokemon.IPokemonDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface IGetPagedPokemonUseCase {
    fun execute(): Flow<PagingData<PokemonItemUIModel>>
}

@OptIn(ExperimentalPagingApi::class)
class GetPagedPokemonUseCase(
    private val pokemonRepository: IPokemonDataSource.Repository,
    private val context: Context
) : IGetPagedPokemonUseCase {

    override fun execute(): Flow<PagingData<PokemonItemUIModel>> =
        Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = false
            ),
            remoteMediator = PokemonRemoteMediator(pokemonRepository, context),
            pagingSourceFactory = { pokemonRepository.getPokemonsLocalPaged() }
        ).flow.map { pagingData ->
            pagingData.map { it.toUIModel() }
        }

    companion object {
        private const val PAGE_SIZE = 15
    }
}
