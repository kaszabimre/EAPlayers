package io.imrekaszab.eaplayers.domain.service

import io.imrekaszab.eaplayers.domain.action.EAPlayerAction
import io.imrekaszab.eaplayers.domain.model.Player
import io.imrekaszab.eaplayers.domain.model.toPlayer
import io.imrekaszab.eaplayers.domain.store.EAPlayerStore
import io.imrekaszab.eaplayers.network.PlayerApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext

class EAPlayerService(private val playerApi: PlayerApi) : EAPlayerAction, EAPlayerStore {

    private val playersStateFlow = MutableStateFlow<List<Player>>(emptyList())

    private val isDarkModeActiveStateFlow = MutableStateFlow(false)

    override suspend fun refreshPlayers(search: String) = withContext(Dispatchers.Default) {
        val response = playerApi.getPlayersResponse(search = search)
        val players = response?.items?.map { it.toPlayer() }
        players ?: return@withContext
        playersStateFlow.emit(players)
    }

    override suspend fun selectPlayer(player: Player) {
        val teamMates =
            playerApi.getPlayersResponseByTeam(player.team.id)?.items?.map { it.toPlayer() }
                ?: emptyList()
        val currentPlayers = playersStateFlow.value.toMutableList()
        val playerIndex = currentPlayers.indexOfFirst { it.id == player.id }

        if (playerIndex != -1) {
            currentPlayers[playerIndex] = player.copy(teamMates = teamMates)
            playersStateFlow.emit(currentPlayers)
        }
    }

    override suspend fun toggleDarkMode() = withContext(Dispatchers.Default) {
        isDarkModeActiveStateFlow.emit(!isDarkModeActiveStateFlow.first())
    }

    override fun getPlayerList(): Flow<List<Player>> = playersStateFlow

    override fun isDarkModeActive(): Flow<Boolean> = isDarkModeActiveStateFlow
}
