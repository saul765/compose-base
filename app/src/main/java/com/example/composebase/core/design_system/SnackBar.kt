package com.example.composebase.core.design_system


import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarData
import androidx.compose.runtime.Composable


@Composable
fun SnackBar(data: SnackbarData) {


    MaterialTheme(typography = typography) {
        Snackbar(snackbarData = data)
    }
}