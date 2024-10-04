package navigation

import PlayerDetailScreen
import PlayerListScreen
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.imrekaszab.eaplayers.core.util.collectAsStateInLifecycle
import io.imrekaszab.eaplayers.core.util.invoke
import io.imrekaszab.eaplayers.core.util.launchOnDefault
import io.imrekaszab.eaplayers.viewmodel.AppViewModel
import io.imrekaszab.eaplayers.viewmodel.PlayerListViewModel
import kotlinx.coroutines.CoroutineScope
import org.koin.compose.koinInject
import theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navigation() {
    val navController = rememberNavController()
    val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val viewModel = koinInject<AppViewModel>()
    val listviewModel: PlayerListViewModel = koinInject<PlayerListViewModel>()
    val coroutineScope = rememberCoroutineScope()
    val uiState by viewModel.uiState.collectAsStateInLifecycle()

    AppTheme(darkTheme = uiState.darkMode, content = {
        ContainerScreen(content = {
            NavHost(
                navController = navController,
                startDestination = EAPlayersScreens.ListScreen.route,
                enterTransition = {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(500)
                    )
                },
                exitTransition = {
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(500)
                    )
                },
                popEnterTransition = {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right,
                        animationSpec = tween(500)
                    )
                },
                popExitTransition = {
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right,
                        animationSpec = tween(500)
                    )
                }
            ) {
                composable(EAPlayersScreens.ListScreen.route) {
                    PlayerListScreen(
                        navHostController = navController,
                        viewModel = listviewModel
                    )
                }
                composable(route = EAPlayersScreens.DetailsScreen.route) {
                    PlayerDetailScreen(navController)
                }
            }
            if (bottomSheetState.isVisible) {
                Modal(
                    bottomSheetState = bottomSheetState,
                    appViewModel = viewModel,
                    isDarkMode = uiState.darkMode,
                    coroutineScope = coroutineScope
                )
            }
        },
            onIconClick = {
                coroutineScope.launchOnDefault {
                    if (!bottomSheetState.isVisible) {
                        bottomSheetState.show()
                    } else {
                        bottomSheetState.hide()
                    }
                }
            })
    }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Modal(
    bottomSheetState: SheetState,
    appViewModel: AppViewModel,
    isDarkMode: Boolean,
    coroutineScope: CoroutineScope
) {
    ModalBottomSheet(
        sheetState = bottomSheetState,
        containerColor = AppTheme.colorScheme.background,
        onDismissRequest = {
            coroutineScope.launchOnDefault {
                bottomSheetState.hide()
            }
        }) {
        Column(
            modifier = Modifier.background(AppTheme.colorScheme.background)
                .padding(horizontal = 16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = if (isDarkMode) "Dark Mode" else "Light Mode",
                    style = AppTheme.typography.button.medium,
                    color = AppTheme.colorScheme.onBackground
                )
                Switch(
                    checked = isDarkMode,
                    onCheckedChange = { appViewModel.switchDarkMode() },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = AppTheme.colorScheme.primary,
                        uncheckedThumbColor = AppTheme.colorScheme.onSurface,
                        checkedTrackColor = AppTheme.colorScheme.primaryContainer,
                        uncheckedTrackColor = AppTheme.colorScheme.surfaceVariant
                    )
                )
            }
        }
    }
}

@Composable
fun ContainerScreen(
    content: @Composable () -> Unit,
    onIconClick: () -> Unit = {},
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppTheme.colorScheme.background)
    ) {
        content()
        IconButton(
            modifier = Modifier
                .align(Alignment.BottomCenter).size(40.dp)
                .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
                .background(AppTheme.colorScheme.primary),
            onClick = onIconClick
        ) {
            Icon(
                imageVector = Icons.Default.Menu,
                tint = AppTheme.colorScheme.secondary,
                contentDescription = null,
            )
        }
    }
}
