package com.example.composebase.data.datasources

import com.example.composebase.domain.model.Country
import com.example.composebase.domain.model.CountryDetail

interface ICountriesDataSource {

    interface Remote {
        suspend fun getCountries(): List<Country>
        suspend fun getCountry(code: String): CountryDetail?

    }

    interface Repository {
        suspend fun getCountriesAsync(): List<Country>
        suspend fun getCountryAsync(code: String): CountryDetail?

    }
}