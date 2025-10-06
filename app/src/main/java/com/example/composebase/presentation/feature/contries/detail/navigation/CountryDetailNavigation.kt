package com.example.composebase.presentation.feature.contries.detail.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.composebase.presentation.feature.contries.detail.CountryDetailScreen
import kotlinx.serialization.Serializable

@Serializable
data class CountryDetailRoute(
    val countryId: String
)

fun NavGraphBuilder.toCountryDetail() {
    composable<CountryDetailRoute> { CountryDetailScreen() }
}
