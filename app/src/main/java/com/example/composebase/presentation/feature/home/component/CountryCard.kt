package com.example.composebase.presentation.feature.home.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.composebase.presentation.theme.customDimenSp
import com.example.composebase.presentation.theme.customDimens

@Composable
fun CountryCard(
    flag: String,
    name: String,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(MaterialTheme.customDimens.dimen100)
            .clickable(onClick = onClick)
    ) {
        Row(
            Modifier
                .fillMaxSize()
                .padding(MaterialTheme.customDimens.dimen14),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
                Text(
                    text = flag,
                    fontSize = MaterialTheme.customDimenSp.dimen30
                )
            }
            Text(
                modifier = Modifier.weight(3f),
                text = name,
                style = MaterialTheme.typography.bodyLarge.copy(textAlign = TextAlign.Center)
            )
        }
    }
}

@Preview
@Composable
private fun CountryCardPreview() {
    CountryCard(flag = "🇨🇦", name = "Canada")
}