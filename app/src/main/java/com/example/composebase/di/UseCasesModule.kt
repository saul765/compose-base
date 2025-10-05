package com.example.composebase.di

import com.example.composebase.domain.usecases.GetCountriesUseCase
import com.example.composebase.domain.usecases.IGetCountriesUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

object UseCasesModule {

    val module = module {
        singleOf(::GetCountriesUseCase).bind(IGetCountriesUseCase::class)
    }
}