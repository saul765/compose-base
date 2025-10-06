package com.example.composebase.core.utils.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

@Composable
fun Modifier.topBorder(width: Dp, color: Color) = drawBehind {
    val strokeWidth = width.toPx()
    // línea centrada en el grosor, pegada al top
    drawLine(
        color = color,
        start = Offset(0f, strokeWidth / 2f),
        end = Offset(size.width, strokeWidth / 2f),
        strokeWidth = strokeWidth
    )
}