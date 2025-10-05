package com.example.composebase.domain.usecases

import com.example.composebase.domain.model.Country

interface IGetCountriesUseCase {
    suspend operator fun invoke(): List<Country>
}