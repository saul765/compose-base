package com.example.composebase.di

import com.example.composebase.data.datasources.CountriesRemoteDataSource
import com.example.composebase.data.datasources.ICountriesDataSource
import com.example.composebase.domain.repositories.CountriesRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

object RepositoriesModule {

    val module = module {
        singleOf(::CountriesRemoteDataSource).bind(ICountriesDataSource.Remote::class)
        singleOf(::CountriesRepository).bind(ICountriesDataSource.Repository::class)
    }
}