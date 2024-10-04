import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import io.imrekaszab.eaplayers.core.util.collectAsStateInLifecycle
import io.imrekaszab.eaplayers.core.util.invoke
import io.imrekaszab.eaplayers.viewmodel.PlayerListViewModel
import navigation.EAPlayersScreens
import theme.AppTheme
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

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppTheme.colorScheme.background)
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(AppTheme.dimens.margin.tiny),
            modifier = Modifier.fillMaxSize()
        ) {
            item {
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(AppTheme.dimens.margin.tiny),
                    value = searchQuery,
                    textStyle = AppTheme.typography.body.medium,
                    onValueChange = { searchQuery = it },
                    colors = TextFieldDefaults.colors().copy(
                        // Text Colors
                        focusedTextColor = AppTheme.colorScheme.primaryContainer,
                        unfocusedTextColor = AppTheme.colorScheme.primaryContainer,
                        disabledTextColor = AppTheme.colorScheme.onSurface.copy(alpha = 0.38f),
                        errorTextColor = AppTheme.colorScheme.error,

                        // Container Colors
                        focusedContainerColor = AppTheme.colorScheme.surface,
                        unfocusedContainerColor = AppTheme.colorScheme.surfaceVariant,
                        disabledContainerColor = AppTheme.colorScheme.surface.copy(alpha = 0.12f),
                        errorContainerColor = AppTheme.colorScheme.errorContainer,

                        // Cursor and Text Selection Colors
                        cursorColor = AppTheme.colorScheme.primary,
                        errorCursorColor = AppTheme.colorScheme.error,
                        textSelectionColors = LocalTextSelectionColors.current,

                        // Indicator (Underline) Colors
                        focusedIndicatorColor = AppTheme.colorScheme.primary,
                        unfocusedIndicatorColor = AppTheme.colorScheme.outline,
                        disabledIndicatorColor = AppTheme.colorScheme.onSurface.copy(alpha = 0.38f),
                        errorIndicatorColor = AppTheme.colorScheme.error,

                        // Label Colors (Fix for Light Mode)
                        focusedLabelColor = AppTheme.colorScheme.onSurface,
                        unfocusedLabelColor = AppTheme.colorScheme.onSurfaceVariant,
                        disabledLabelColor = AppTheme.colorScheme.onSurface.copy(alpha = 0.38f),
                        errorLabelColor = AppTheme.colorScheme.error
                    )
                )
            }

            if (uiState.loading) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(AppTheme.colorScheme.background)
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.Center),
                            color = AppTheme.colorScheme.onPrimary
                        )
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
}
