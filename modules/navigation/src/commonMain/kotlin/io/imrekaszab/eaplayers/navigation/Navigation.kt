package io.imrekaszab.eaplayers.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.imrekaszab.eaplayers.theme.AppTheme
import io.imrekaszab.eaplayers.theme.navigation.EAPlayersScreens
import io.imrekaszab.eaplayers.view.PlayerDetailScreen
import io.imrekaszab.eaplayers.view.PlayerListScreen

private const val ANIMATION_DURATION = 500

@Composable
fun Navigation() {
    val navController = rememberNavController()
    AppTheme(
        content = {
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
                    PlayerListScreen(navHostController = navController)
                }
                composable(route = EAPlayersScreens.DetailsScreen.route) {
                    PlayerDetailScreen(navController)
                }
            }
        }
    )
}
