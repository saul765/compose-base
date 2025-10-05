package com.example.composebase.data.api

import com.example.composebase.CountriesQuery
import com.example.composebase.CountryQuery

interface ICountryAPI {
    suspend fun getCountries(): List<CountriesQuery.Country>?
    suspend fun getCountry(code: String): CountryQuery.Country?
}
