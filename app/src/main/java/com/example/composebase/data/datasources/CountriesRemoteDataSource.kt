package com.example.composebase.data.datasources

import com.example.composebase.data.api.ICountryAPI
import com.example.composebase.data.mappers.toCountry
import com.example.composebase.domain.model.Country
import com.example.composebase.domain.model.CountryDetail

class CountriesRemoteDataSource(private val countryAPI: ICountryAPI) :
    ICountriesDataSource.Remote {
    override suspend fun getCountries(): List<Country> =
        countryAPI.getCountries()?.map { it.toCountry() }.orEmpty()

    override suspend fun getCountry(code: String): CountryDetail? =
        countryAPI.getCountry(code)?.toCountry()
}
