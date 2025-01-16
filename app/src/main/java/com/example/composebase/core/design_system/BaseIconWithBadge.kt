package com.example.composebase.core.design_system

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun BaseIconWithBadge(
    modifier: Modifier = Modifier,
    notificationCount: Int,
    background: Color = Color.Red,
    textColor: Color = Color.White,
    content: @Composable () -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        BadgedBox(
            badge = {
                Badge(
                    containerColor = background,
                    contentColor = textColor,
                    modifier = Modifier
                        .offset(x = (-6).dp, y = (-4).dp)
                ) {
                    Text(text = notificationCount.toString())
                }
            }) {
            content()
        }

    }
}