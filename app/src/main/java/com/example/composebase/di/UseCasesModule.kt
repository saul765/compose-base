package com.example.composebase.di

import com.example.composebase.core.usecases.GetHomePokemonUseCase
import com.example.composebase.core.usecases.GetPokemonUseCase
import com.example.composebase.core.usecases.IGetHomePokemonUseCase
import com.example.composebase.core.usecases.IGetPokemonUseCase
import com.example.composebase.core.usecases.IWorkerPokemonUseCase
import com.example.composebase.core.usecases.WorkerPokemonUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

object UseCasesModule {

    val module = module {

        singleOf(::WorkerPokemonUseCase).bind(IWorkerPokemonUseCase::class)

        singleOf(::GetPokemonUseCase).bind(IGetPokemonUseCase::class)

        singleOf(::GetHomePokemonUseCase).bind(IGetHomePokemonUseCase::class)
    }
}