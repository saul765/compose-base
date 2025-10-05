package com.example.composebase.domain.repositories

import com.example.composebase.data.datasources.ICountriesDataSource
import com.example.composebase.domain.model.Country
import com.example.composebase.domain.model.CountryDetail

class CountriesRepository(private val remoteDataSource: ICountriesDataSource.Remote) :
    ICountriesDataSource.Repository {
    override suspend fun getCountriesAsync(): List<Country> = remoteDataSource.getCountries()

    override suspend fun getCountryAsync(code: String): CountryDetail? =
        remoteDataSource.getCountry(code)
}
