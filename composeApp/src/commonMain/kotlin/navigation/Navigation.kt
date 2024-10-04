package navigation

import PlayerDetailScreen
import PlayerListScreen
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.imrekaszab.eaplayers.viewmodel.PlayerListViewModel
import org.koin.compose.koinInject
import theme.AppTheme

private const val ANIMATION_DURATION = 500

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val listviewModel: PlayerListViewModel = koinInject<PlayerListViewModel>()

    AppTheme(content = {
        NavHost(
            navController = navController,
            startDestination = EAPlayersScreens.ListScreen.route,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(ANIMATION_DURATION)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(ANIMATION_DURATION)
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(ANIMATION_DURATION)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(ANIMATION_DURATION)
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
    }
    )
}
