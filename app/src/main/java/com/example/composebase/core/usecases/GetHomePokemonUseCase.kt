package com.example.composebase.core.usecases

import android.content.Context
import androidx.annotation.VisibleForTesting
import androidx.work.BackoffPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.composebase.core.model.toEntity
import com.example.composebase.core.repositories.pokemon.IPokemonDataSource
import com.example.composebase.core.workers.PokemonWorker
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import java.util.concurrent.TimeUnit

interface IGetHomePokemonUseCase {
    suspend fun execute(): Flow<Unit>
}

class GetHomePokemonUseCase(
    private val pokemonRepository: IPokemonDataSource.Repository,
    private val context: Context
) : IGetHomePokemonUseCase {
    companion object {
        const val POKEMON_FIRST_CHUNK = 15
        const val POKEMON_OFFSET = 0
    }

    override suspend fun execute(): Flow<Unit> = flow {

        if (pokemonRepository.getPokemonsLocal().first().isEmpty()) {

            // Get the first chunk of pokemons
            val pokemonResult =
                pokemonRepository.getPokemonsAsync(POKEMON_FIRST_CHUNK, POKEMON_OFFSET)

            // Get the details of the first chunk of pokemons
            val pokemonItems = pokemonResult.results.map { pokemon ->
                pokemonRepository.getPokemonDetailAsync(pokemon.url)
            }

            // Save the first chunk of pokemons to the local database
            val pokemonEntities = pokemonItems.map { pokemonItem ->
                pokemonItem.toEntity()
            }

            pokemonRepository.savePokemonsLocal(pokemonEntities)

            // Load the rest of the pokemons in the background
            pokedexLoadBatch(context)
            emit(Unit)
        }
    }
}

@VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
fun pokedexLoadBatch(context: Context) {
    val workRequest = OneTimeWorkRequestBuilder<PokemonWorker>().setBackoffCriteria(
        BackoffPolicy.EXPONENTIAL, // or BackoffPolicy.LINEAR
        15, // Initial delay duration
        TimeUnit.SECONDS // Time unit for the delay
    ).build()
    WorkManager.getInstance(context).enqueue(workRequest)
}

