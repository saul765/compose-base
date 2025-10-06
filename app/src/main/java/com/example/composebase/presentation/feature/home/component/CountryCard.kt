package com.example.composebase.presentation.feature.home.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.composebase.presentation.theme.customDimenSp
import com.example.composebase.presentation.theme.customDimens

@Composable
fun CountryItem(
    modifier: Modifier = Modifier,
    flag: String,
    name: String,
    capital: String,
    onClick: () -> Unit = {}
) {
    Surface(modifier = modifier) {
        Row(
            Modifier
                .padding(MaterialTheme.customDimens.dimen16)
                .fillMaxWidth()
                .clickable(onClick = onClick),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = flag,
                fontSize = MaterialTheme.customDimenSp.dimen30
            )
            Spacer(modifier = Modifier.width(MaterialTheme.customDimens.dimen16))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = name,
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.width(MaterialTheme.customDimens.dimen16))
                Text(text = capital)
            }
        }
    }
}

@Preview
@Composable
private fun CountryCardPreview() {
    CountryItem(flag = "🇨🇦", name = "Canada", capital = "Ottawa")
}