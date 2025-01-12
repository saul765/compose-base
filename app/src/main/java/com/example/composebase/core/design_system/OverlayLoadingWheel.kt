package com.example.composebase.core.design_system

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun OverlayLoadingWheel(
    modifier: Modifier = Modifier,
    message: String? = null
) {

    MaterialTheme {
        Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
            Surface(
                shape = RoundedCornerShape(60.dp),
                shadowElevation = 8.dp,
                color = MaterialTheme.colorScheme.surface.copy(alpha = 0.83f),
                modifier = modifier
                    .size(60.dp),
            ) {
                GAFLoadingWheel()
            }
            message?.let {
                ProcessMessage(modifier = Modifier.padding(vertical = 5.dp), message = message)
            }

        }
    }
}

