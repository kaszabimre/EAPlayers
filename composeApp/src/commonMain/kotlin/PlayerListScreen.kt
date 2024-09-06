import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import io.imrekaszab.eaplayers.core.util.collectAsStateInLifecycle
import io.imrekaszab.eaplayers.core.util.invoke
import io.imrekaszab.eaplayers.viewmodel.PlayerListViewModel
import navigation.EAPlayersScreens

@Composable
fun PlayerListScreen(
    viewModel: PlayerListViewModel,
    navHostController: NavHostController
) {
    val uiState by viewModel.uiState.collectAsStateInLifecycle()

    var searchQuery by remember { mutableStateOf("") }

    LaunchedEffect(searchQuery) {
        viewModel.refreshPlayers(searchQuery)
    }
    LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        item {
            TextField(
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                value = searchQuery,
                onValueChange = { searchQuery = it }
            )
        }
        if (uiState.loading) {
            item {
                Box(modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            }
        } else {
            items(uiState.players) { player ->
                PlayerItemView(
                    player = player,
                    onPlayerClick = {
                        viewModel.selectPlayer(player)
                        navHostController.navigate(EAPlayersScreens.DetailsScreen.createRoute(player.id))
                    }
                )
            }
        }
    }
}
