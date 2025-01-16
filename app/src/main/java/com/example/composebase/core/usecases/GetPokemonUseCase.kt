package com.example.composebase.core.usecases


import com.example.composebase.core.database.entity.PokemonEntity
import com.example.composebase.core.repositories.pokemon.IPokemonDataSource
import kotlinx.coroutines.flow.Flow


interface IGetPokemonUseCase {
    fun execute(): Flow<List<PokemonEntity>>
}

class GetPokemonUseCase(private val pokemonRepository: IPokemonDataSource.Repository) :
    IGetPokemonUseCase {
    override fun execute(): Flow<List<PokemonEntity>> = pokemonRepository.getPokemonsLocal()
}