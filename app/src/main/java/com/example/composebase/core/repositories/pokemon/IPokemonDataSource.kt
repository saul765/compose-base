package com.example.composebase.core.repositories.pokemon

import com.example.composebase.core.database.entity.PokemonEntity
import com.example.composebase.core.model.PokemonItem
import com.example.composebase.core.model.PokemonResult
import com.example.composebase.core.webservice.dto.responses.PokemonItemDTO
import com.example.composebase.core.webservice.dto.responses.PokemonResultDTO
import kotlinx.coroutines.flow.Flow

interface IPokemonDataSource {

    interface Remote {
        suspend fun getPokemons(limit: Int, offset: Int): PokemonResultDTO

        suspend fun getPokemonDetail(pokemonUrl: String): PokemonItemDTO
    }

    interface Local {
         fun getPokemons(): Flow<List<PokemonEntity>>

        suspend fun savePokemons(pokemons: List<PokemonEntity>)

        suspend fun getPokemonById(id: Int): PokemonEntity

        suspend fun getTotalNumberOfPokemons(): Int
    }

    interface Repository {
        suspend fun getPokemonsAsync(limit: Int, offset: Int): PokemonResult

        fun getPokemonsLocal(): Flow<List<PokemonEntity>>

        suspend fun savePokemonsLocal(pokemons: List<PokemonEntity>)

        suspend fun getPokemonByIdLocal(id: Int): PokemonEntity

        suspend fun getPokemonDetailAsync(pokemonUrl: String): PokemonItem

        suspend fun getTotalNumberOfPokemonsLocal(): Int
    }
}