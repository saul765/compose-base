package com.example.composebase.core.repositories.pokemon

import com.example.composebase.core.database.dao.IPokemonDao
import com.example.composebase.core.database.entity.PokemonEntity
import kotlinx.coroutines.flow.Flow

class PokemonLocalDataSource(private val pokemonDAO: IPokemonDao) : IPokemonDataSource.Local {
    override fun getPokemons(): Flow<List<PokemonEntity>> = pokemonDAO.findAll()

    override suspend fun savePokemons(pokemons: List<PokemonEntity>) = pokemonDAO.saveAll(pokemons)

    override suspend fun getPokemonById(id: Int): PokemonEntity = pokemonDAO.findPokemonById(id)

    override suspend fun getTotalNumberOfPokemons(): Int = pokemonDAO.getTotalNumberOfPokemons()
}