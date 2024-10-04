import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import io.imrekaszab.eaplayers.core.util.invoke
import io.imrekaszab.eaplayers.viewmodel.PlayerDetailViewModel
import navigation.EAPlayersScreens
import org.koin.compose.koinInject
import theme.AppTheme

@Composable
fun PlayerDetailScreen(
    navHostController: NavHostController,
    viewModel: PlayerDetailViewModel = koinInject<PlayerDetailViewModel>()
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
                modifier = Modifier.padding(4.dp),
                onClick = { navHostController.popBackStack() }
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = AppTheme.colorScheme.onBackground
                )
            }
        }
    ) { innerPadding ->
        when {
            uiState.loading -> CircularProgressIndicator()

            uiState.error != null ->
                Text(text = "Error: ${uiState.error}")

            uiState.player != null -> {
                PlayerDetailView(
                    modifier = Modifier.padding(innerPadding),
                    player = uiState.player!!,
                    onTeamMateSelected = {
                        viewModel.selectPlayer(it)
                        navHostController.navigate(
                            EAPlayersScreens.DetailsScreen.createRoute(it.id),
                        )
                    }
                )
            }
        }
    }
}
