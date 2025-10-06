package com.example.composebase.presentation.feature.contries.detail

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.composebase.presentation.theme.ComposeBaseTheme

@Composable
fun CountryDetailScreen() {
    Text("Country Detail Screen")
}

@Preview(showBackground = true)
@Composable
private fun CountryDetailScreenPreview() {
    ComposeBaseTheme {
        CountryDetailScreen()
    }
}
