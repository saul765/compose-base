package com.example.composebase.feature.home

import android.Manifest
import android.os.Build
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.example.composebase.R
import com.example.composebase.core.compose.AppScaffold
import com.example.composebase.core.design_system.AlertDialogInformation
import com.example.composebase.core.design_system.BasePermission
import com.example.composebase.core.design_system.icon.BaseIcons
import com.example.composebase.core.model.uiModel.PokemonItemUIModel
import com.example.composebase.core.utils.capitalize
import com.example.composebase.core.utils.extensions.goToAppSettings
import com.example.composebase.feature.home.component.PokemonItem
import com.example.composebase.feature.home.component.PokemonTopBar
import com.example.composebase.ui.theme.ComposeBaseTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import kotlinx.coroutines.flow.flowOf
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun HomeScreen(
    onSearchClick: () -> Unit = {},
    viewModel: HomeViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val pokemons = uiState.pokemons.collectAsLazyPagingItems()

    AppScaffold(
        topBar = {
            PokemonTopBar(
                onSearchClick = onSearchClick,
                notificationCount = pokemons.itemCount
            )
        }
    ) {
        HomeScreenContent(
            pokemons = pokemons,
            onEvent = viewModel::onEvent
        )
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
private fun HomeScreenContent(
    pokemons: LazyPagingItems<PokemonItemUIModel>,
    onEvent: (HomeScreenUiEvent) -> Unit
) {
    val context = LocalContext.current
    val permissionRationale = stringResource(id = R.string.pokemon_rationale_permission)
    var permissionDialogType by rememberSaveable { mutableStateOf<PermissionDialogType?>(null) }
    var requestPermissionAgain by remember { mutableStateOf<(() -> Unit)?>(null) }

    BasePermission(
        permissions = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            listOf(Manifest.permission.POST_NOTIFICATIONS)
        } else {
            emptyList()
        },
        onGranted = {
            permissionDialogType = null
            onEvent(HomeScreenUiEvent.OnPermissionGranted)
        },
        onShouldShowRationale = { requestPermission ->
            requestPermissionAgain = requestPermission
            permissionDialogType = PermissionDialogType.Rationale
        },
        onPermanentlyDenied = {
            requestPermissionAgain = null
            permissionDialogType = PermissionDialogType.GoToSettings
        }
    )

    permissionDialogType?.let { dialogType ->
        AlertDialogInformation(
            isVisible = true,
            onDismissRequest = { permissionDialogType = null },
            onConfirmation = {
                permissionDialogType = null
                when (dialogType) {
                    PermissionDialogType.Rationale -> requestPermissionAgain?.invoke()
                    PermissionDialogType.GoToSettings -> context.goToAppSettings()
                }
            },
            dialogTitle = stringResource(R.string.alert_dialog_permissions_title),
            dialogText = permissionRationale,
            icon = BaseIcons.Info,
            confirmText = stringResource(
                if (dialogType == PermissionDialogType.Rationale) {
                    R.string.alert_dialog_accept
                } else {
                    R.string.alert_dialog_permissions_rationale_settings
                }
            )
        )
    }

    HomePokemonsGrid(
        pokemons = pokemons,
        onCardClicked = { pokemon ->
            Toast.makeText(context, pokemon.name.capitalize(), Toast.LENGTH_SHORT).show()
        }
    )
}

@Composable
private fun HomePokemonsGrid(
    pokemons: LazyPagingItems<PokemonItemUIModel>,
    onCardClicked: (PokemonItemUIModel) -> Unit
) {
    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize(),
        columns = GridCells.Fixed(POKEMON_ROW_ITEMS),
        contentPadding = PaddingValues(10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(
            count = pokemons.itemCount,
            key = pokemons.itemKey { it.id }
        ) { index ->
            pokemons[index]?.let { item ->
                PokemonItem(
                    pokemonItem = item,
                    modifier = Modifier.clickable { onCardClicked(item) }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    val pokemons = flowOf(
        PagingData.from(
            listOf(
                PokemonItemUIModel(id = 1, name = "Bulbasaur", imageUrl = ""),
                PokemonItemUIModel(id = 2, name = "Ivysaur", imageUrl = ""),
                PokemonItemUIModel(id = 3, name = "Venusaur", imageUrl = "")
            )
        )
    ).collectAsLazyPagingItems()

    ComposeBaseTheme {
        HomePokemonsGrid(pokemons = pokemons, onCardClicked = {})
    }
}

private const val POKEMON_ROW_ITEMS = 3

private enum class PermissionDialogType {
    Rationale,
    GoToSettings
}
