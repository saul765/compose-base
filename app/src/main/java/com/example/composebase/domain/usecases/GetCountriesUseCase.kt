package com.example.composebase.domain.usecases

import com.example.composebase.data.datasources.ICountriesDataSource
import com.example.composebase.domain.model.Country

class GetCountriesUseCase(private val countriesRepository: ICountriesDataSource.Repository) :
    IGetCountriesUseCase {
    override suspend fun invoke(): List<Country> = countriesRepository.getCountriesAsync()
}
