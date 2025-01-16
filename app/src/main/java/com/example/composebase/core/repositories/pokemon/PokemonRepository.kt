package com.example.composebase.core.repositories.pokemon

import com.example.composebase.core.database.entity.PokemonEntity
import com.example.composebase.core.model.PokemonItem
import com.example.composebase.core.model.PokemonResult
import com.example.composebase.core.webservice.dto.responses.toDomain
import kotlinx.coroutines.flow.Flow

class PokemonRepository(
    private val remoteDataSource: IPokemonDataSource.Remote,
    private val localDataSource: IPokemonDataSource.Local
) : IPokemonDataSource.Repository {
    override suspend fun getPokemonsAsync(limit: Int, offset: Int): PokemonResult =
        remoteDataSource.getPokemons(limit, offset).toDomain()

    override  fun getPokemonsLocal(): Flow<List<PokemonEntity>> =
        localDataSource.getPokemons()

    override suspend fun savePokemonsLocal(pokemons: List<PokemonEntity>) =
        localDataSource.savePokemons(pokemons)

    override suspend fun getPokemonByIdLocal(id: Int): PokemonEntity =
        localDataSource.getPokemonById(id)

    override suspend fun getPokemonDetailAsync(pokemonUrl: String): PokemonItem =
        remoteDataSource.getPokemonDetail(pokemonUrl).toDomain()

    override suspend fun getTotalNumberOfPokemonsLocal(): Int =
        localDataSource.getTotalNumberOfPokemons()

}