package com.example.composebase.presentation.feature.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.composebase.core.utils.events.UiEventHandler
import com.example.composebase.domain.model.Country
import com.example.composebase.presentation.feature.home.component.CountryCard
import com.example.composebase.presentation.theme.ComposeBaseTheme
import com.example.composebase.presentation.theme.customDimens
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(viewModel: HomeViewModel = koinViewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    UiEventHandler(viewModel.uiEvents) {
        HomeScreenContent(
            uiState = uiState,
            onEvent = viewModel::onEvent
        )
    }
}

@Composable
private fun HomeScreenContent(uiState: HomeUiState, onEvent: (HomeUiEvent) -> Unit = {}) {
    LaunchedEffect(Unit) {
        onEvent(HomeUiEvent.OnStart)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(MaterialTheme.customDimens.dimen16)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.customDimens.dimen16)
        ) {
            items(uiState.countries, key = { it.code }) { country ->
                CountryCard(
                    flag = country.emoji,
                    name = country.name,
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
