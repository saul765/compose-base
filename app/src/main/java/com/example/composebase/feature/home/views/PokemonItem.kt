package com.example.composebase.feature.home.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.composebase.core.model.uiModel.PokemonItemUIModel
import com.example.composebase.core.utils.FormatUtils
import com.example.composebase.core.utils.capitalize
import com.example.composebase.ui.theme.ComposeBaseTheme
import com.example.composebase.ui.theme.PokemonCardBackgroundColor

@Composable
fun PokemonItem(
    pokemonItem: PokemonItemUIModel,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .height(150.dp)
            .width(120.dp),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 3.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = FormatUtils.formatPokemonNumber(pokemonItem.id),
                    modifier = Modifier.padding(end = 10.dp)
                )
            }
            Box(
                modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .clip(RoundedCornerShape(15.dp, 15.dp, 0.dp, 0.dp))
                    .align(Alignment.BottomCenter)
                    .background(color = PokemonCardBackgroundColor)
            ) {}
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(pokemonItem.imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                modifier = Modifier
                    .size(85.dp)
                    .align(Alignment.Center)
            )

            Text(
                text = pokemonItem.name.capitalize(),
                fontSize = 16.sp,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .offset(y = (-5).dp)
            )

        }
    }
}

@Composable
@Preview(showBackground = true)
fun PokemonItemPreview() {

    val uiState =
        PokemonItemUIModel(
            id = 1,
            name = "Bulbasaur",
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png"
        )


    ComposeBaseTheme {
        PokemonItem(
            pokemonItem = uiState
        )
    }
}