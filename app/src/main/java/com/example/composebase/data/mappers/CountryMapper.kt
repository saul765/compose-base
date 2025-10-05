package com.example.composebase.data.mappers

import com.example.composebase.CountriesQuery
import com.example.composebase.CountryQuery
import com.example.composebase.domain.model.Country
import com.example.composebase.domain.model.CountryDetail

fun CountriesQuery.Country.toCountry() = Country(
    code = code,
    name = name,
    emoji = emoji,
    capital = capital.orEmpty()
)


fun CountryQuery.Country.toCountry() = CountryDetail(
    code = code,
    name = name
)
