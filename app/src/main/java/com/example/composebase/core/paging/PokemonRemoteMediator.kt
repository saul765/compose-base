package com.example.composebase.core.paging

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.work.BackoffPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.example.composebase.core.database.entity.PokemonEntity
import com.example.composebase.core.model.toEntity
import com.example.composebase.core.repositories.pokemon.IPokemonDataSource
import com.example.composebase.core.workers.PokemonWorker
import java.util.concurrent.TimeUnit

@OptIn(ExperimentalPagingApi::class)
class PokemonRemoteMediator(
    private val pokemonRepository: IPokemonDataSource.Repository,
    private val context: Context
) : RemoteMediator<Int, PokemonEntity>() {

    override suspend fun initialize(): InitializeAction {
        // Skip network REFRESH if Room already has data (e.g. Worker pre-fetched it)
        val hasLocalData = pokemonRepository.getTotalNumberOfPokemonsLocal() > 0
        return if (hasLocalData) InitializeAction.SKIP_INITIAL_REFRESH
        else InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PokemonEntity>
    ): MediatorResult {
        return try {
            val offset = when (loadType) {
                LoadType.REFRESH -> 0
                // Prepend is not supported
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                // Offset is the total number of items already stored in Room
                LoadType.APPEND -> pokemonRepository.getTotalNumberOfPokemonsLocal()
            }

            val limit = state.config.pageSize

            // Fetch pokemon list from network
            val pokemonResult = pokemonRepository.getPokemonsAsync(limit, offset)

            // No results means we've reached the end of pagination
            if (pokemonResult.results.isEmpty()) {
                return MediatorResult.Success(endOfPaginationReached = true)
            }

            // Fetch detail for each pokemon
            val pokemonEntities = pokemonResult.results.map { pokemon ->
                pokemonRepository.getPokemonDetailAsync(pokemon.url).toEntity()
            }

            // On REFRESH, clear the table before inserting new data
            if (loadType == LoadType.REFRESH) {
                pokemonRepository.clearPokemonsLocal()
            }

            // Save to Room — PagingSource will emit automatically
            pokemonRepository.savePokemonsLocal(pokemonEntities)

            // On REFRESH, trigger the Worker to pre-fetch remaining pokemons in background
            if (loadType == LoadType.REFRESH) {
                enqueuePokemonWorker(startOffset = pokemonEntities.size)
            }

            MediatorResult.Success(endOfPaginationReached = pokemonEntities.size < limit)
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }

    private fun enqueuePokemonWorker(startOffset: Int) {
        val workRequest = OneTimeWorkRequestBuilder<PokemonWorker>()
            .setInputData(workDataOf(PokemonWorker.OFFSET_KEY to startOffset))
            .setBackoffCriteria(
                BackoffPolicy.EXPONENTIAL,
                15,
                TimeUnit.SECONDS
            )
            .build()
        WorkManager.getInstance(context).enqueue(workRequest)
    }
}
