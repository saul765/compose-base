package com.example.composebase.core.design_system

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseTopAppBar(
    modifier: Modifier = Modifier,
    title: String,
    navigationIcon: ImageVector? = null,
    navigationIconContentDescription: String?,
    actionIcon: @Composable () -> Unit = {},
    onNavigationClick: () -> Unit = {},
    onActionClick: () -> Unit = {},
) {


    MaterialTheme() {
        CenterAlignedTopAppBar(
            modifier = modifier,
            title = { Text(text = title) },
            navigationIcon = {
                navigationIcon?.let {
                    IconButton(onClick = onNavigationClick) {
                        Icon(
                            imageVector = navigationIcon,
                            contentDescription = navigationIconContentDescription,
                        )
                    }
                }
            },
            actions = {
                IconButton(onClick = onActionClick) {
                    actionIcon()
                }
            },
        )
    }
}