package com.example.composebase.feature.home.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composebase.R
import com.example.composebase.core.EMPTY_CHARACTER
import com.example.composebase.core.ZERO_INTEGER
import com.example.composebase.core.design_system.BaseIconWithBadge
import com.example.composebase.core.design_system.icon.BaseIcons
import com.example.composebase.ui.theme.PokedexHomeBackgroundColor

@Composable
fun PokemonTopBar(onSearchClick: () -> Unit = {}, notificationCount: Int = ZERO_INTEGER) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .background(color = PokedexHomeBackgroundColor)
    ) {
        Spacer(modifier = Modifier.size(16.dp))
        Icon(
            painter = painterResource(R.drawable.pokeball),
            contentDescription = EMPTY_CHARACTER,
            tint = Color.White
        )
        Spacer(modifier = Modifier.size(16.dp))
        Text(
            text = stringResource(R.string.pokemon_home_title),
            modifier = Modifier
                .padding(vertical = 16.dp)
                .weight(1f),
            fontSize = 28.sp,
            color = Color.White, fontWeight = FontWeight.Bold
        )
        BaseIconWithBadge(
            modifier = Modifier.clickable { onSearchClick() },
            notificationCount = notificationCount,
            background = Color.White,
            textColor = Color.Black
        ) {
            Icon(
                imageVector = BaseIcons.Search,
                modifier = Modifier.size(35.dp),
                contentDescription = null,
                tint = Color.White
            )
        }
        Spacer(modifier = Modifier.size(16.dp))
    }
}