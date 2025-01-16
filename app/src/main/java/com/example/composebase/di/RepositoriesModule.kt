package com.example.composebase.di

import com.example.composebase.core.repositories.pokemon.IPokemonDataSource
import com.example.composebase.core.repositories.pokemon.PokemonLocalDataSource
import com.example.composebase.core.repositories.pokemon.PokemonRemoteDataSource
import com.example.composebase.core.repositories.pokemon.PokemonRepository
import org.koin.dsl.module

object RepositoriesModule {

    val module = module {


        single<IPokemonDataSource.Repository> { PokemonRepository(get(), get()) }

        single<IPokemonDataSource.Remote> { PokemonRemoteDataSource(get()) }

        single<IPokemonDataSource.Local> { PokemonLocalDataSource(get()) }

    }
}