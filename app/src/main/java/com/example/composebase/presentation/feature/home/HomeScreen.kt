package com.example.composebase.presentation.feature.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.composebase.core.ZERO_INTEGER
import com.example.composebase.domain.model.Country
import com.example.composebase.presentation.feature.home.component.CountryItem
import com.example.composebase.presentation.theme.ComposeBaseTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(viewModel: HomeViewModel = koinViewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    HomeScreenContent(
        uiState = uiState,
        onEvent = viewModel::onEvent
    )
}

@Composable
private fun HomeScreenContent(uiState: HomeUiState, onEvent: (HomeUiEvent) -> Unit = {}) {
    LaunchedEffect(Unit) {
        onEvent(HomeUiEvent.OnStart)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
        ) {
            itemsIndexed(
                uiState.countries,
                key = { _, country -> country.code }) { index, country ->

                if (index != ZERO_INTEGER) HorizontalDivider()
                CountryItem(
                    flag = country.emoji,
                    name = country.name,
                    capital = country.capital,
                    onClick = { onEvent(HomeUiEvent.OnCountryClick(country)) }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenContentPreview() {
    ComposeBaseTheme {
        HomeScreenContent(
            uiState = HomeUiState(
                countries = listOf(
                    Country(
                        code = "CA",
                        name = "Canada",
                        emoji = "🇨🇦",
                        capital = "Ottawa"
                    ),
                    Country(
                        code = "US",
                        name = "United States",
                        emoji = "🇺🇸",
                        capital = "Washington, D.C."
                    ),
                    Country(
                        code = "DE",
                        name = "Germany",
                        emoji = "🇩🇪",
                        capital = "Berlin"
                    ),
                    Country(
                        code = "FR",
                        name = "France",
                        emoji = "🇫🇷",
                        capital = "Paris"
                    ),
                    Country(
                        code = "GB",
                        name = "United Kingdom",
                        emoji = "🇬🇧",
                        capital = "London"
                    ),
                    Country(
                        code = "JP",
                        name = "Japan",
                        emoji = "🇯🇵",
                        capital = "Tokyo"
                    )
                )
            )
        )
    }
}
