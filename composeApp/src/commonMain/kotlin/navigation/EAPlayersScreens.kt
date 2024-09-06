package navigation

sealed class EAPlayersScreens(val route: String) {
    data object ListScreen : EAPlayersScreens("list_screen")
    data object DetailsScreen : EAPlayersScreens("details/{playerId}") {
        fun createRoute(playerId: Int) = "details/$playerId"
    }
}
