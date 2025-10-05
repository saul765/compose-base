package com.example.composebase.data.api

import com.apollographql.apollo.ApolloClient
import com.example.composebase.CountriesQuery
import com.example.composebase.CountryQuery

class CountryAPI(private val apolloClient: ApolloClient) : ICountryAPI {
    override suspend fun getCountries(): List<CountriesQuery.Country>? =
        apolloClient
            .query(CountriesQuery())
            .execute().data?.countries

    override suspend fun getCountry(code: String): CountryQuery.Country? =
        apolloClient
            .query(CountryQuery(code))
            .execute().data?.country

}