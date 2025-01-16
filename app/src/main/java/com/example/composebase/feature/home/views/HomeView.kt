package com.example.composebase.feature.home.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composebase.core.model.uiModel.PokemonItemUIModel
import com.example.composebase.feature.home.state.HomeUiState
import com.example.composebase.ui.theme.ComposeBaseTheme

@Composable
fun HomeView(
    uiState: HomeUiState = HomeUiState(),
    onCardClicked: (PokemonItemUIModel) -> Unit = {}
) {
    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize(),
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(uiState.pokemons) { item ->
            PokemonItem(pokemonItem = item,
                modifier = Modifier.clickable { onCardClicked(item) })
        }
    }
}

@Composable
@Preview(showBackground = true)
fun HomeViewPreview() {

    val uiState = HomeUiState(
        listOf(
            PokemonItemUIModel(
                id = 1,
                name = "Bulbasaur",
                imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png"
            ),
            PokemonItemUIModel(
                id = 2,
                name = "Ivysaur",
                imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/2.png"
            ),
            PokemonItemUIModel(
                id = 3,
                name = "Venusaur",
                imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/3.png"
            ),
        )
    )
    ComposeBaseTheme {
        HomeView(
            uiState = uiState
        )
    }
}