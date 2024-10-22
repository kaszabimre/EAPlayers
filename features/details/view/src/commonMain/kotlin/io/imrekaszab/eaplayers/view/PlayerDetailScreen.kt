package io.imrekaszab.eaplayers.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import io.imrekaszab.eaplayers.core.util.invoke
import io.imrekaszab.eaplayers.core.viewmodel.koinViewModel
import io.imrekaszab.eaplayers.playerdetails.viewmodel.PlayerDetailViewModel
import io.imrekaszab.eaplayers.theme.AppTheme
import io.imrekaszab.eaplayers.theme.navigation.EAPlayersScreens
import io.imrekaszab.eaplayers.theme.widgets.LoadingIndicator

@Composable
fun PlayerDetailScreen(
    navHostController: NavHostController,
    viewModel: PlayerDetailViewModel = koinViewModel<PlayerDetailViewModel>()
) {
    val playerId =
        navHostController.currentBackStackEntry?.arguments?.getString("playerId")?.toInt()

    LaunchedEffect(playerId) {
        playerId?.let {
            viewModel.loadPlayerDetails(it)
        }
    }
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            IconButton(
                modifier = Modifier.padding(AppTheme.dimens.margin.extraTiny),
                onClick = { navHostController.popBackStack() }
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = AppTheme.colorScheme.onBackground
                )
            }
        },
        containerColor = AppTheme.colorScheme.background,
        contentColor = AppTheme.colorScheme.onPrimary
    ) { innerPadding ->
        when {
            uiState.loading -> LoadingIndicator(modifier = Modifier.fillMaxSize())

            uiState.error != null ->
                Text(text = "Error: ${uiState.error}")

            uiState.player != null -> {
                PlayerDetailView(
                    modifier = Modifier.padding(innerPadding),
                    player = uiState.player!!,
                    onTeamMateSelected = {
                        navHostController.navigate(
                            EAPlayersScreens.DetailsScreen.createRoute(it.id),
                        )
                    },
                    isMainStatsExpanded = uiState.isMainStatsExpanded,
                    onMainStatsExpandClick = { viewModel.toggleMainStatsExpanded() },
                    isOtherStatsExpanded = uiState.isOtherStatsExpanded,
                    onOtherStatsExpandClick = { viewModel.toggleOtherStatsExpanded() }
                )
            }
        }
    }
}
