package io.imrekaszab.eaplayers.data.service

import co.touchlab.kermit.Logger
import io.imrekaszab.eaplayers.data.api.PlayerApi
import io.imrekaszab.eaplayers.data.mapper.toPlayer
import io.imrekaszab.eaplayers.domain.action.EAPlayerAction
import io.imrekaszab.eaplayers.domain.model.Player
import io.imrekaszab.eaplayers.domain.store.EAPlayerStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class EAPlayerService(private val playerApi: PlayerApi, private val logger: Logger) : EAPlayerAction, EAPlayerStore {
    private val playersStateFlow = MutableStateFlow<List<Player>>(emptyList())

    override suspend fun refreshPlayers(search: String) = withContext(Dispatchers.Default) {
        val response = playerApi.getPlayersResponse(search = search)
        val players = response?.items?.map { it.toPlayer() }
        players ?: return@withContext
        playersStateFlow.emit(players)
    }

    override suspend fun selectPlayer(player: Player) = withContext(Dispatchers.Default) {
        val teamMates =
            playerApi.getPlayersResponseByTeam(player.team.id)?.items?.map { it.toPlayer() }
                ?: emptyList()

        val currentPlayers = playersStateFlow.value.toMutableList()
        val playerIndex = currentPlayers.indexOfFirst { it.id == player.id }

        if (playerIndex != -1) {
            currentPlayers[playerIndex] = player.copy(teamMates = teamMates)
            playersStateFlow.emit((currentPlayers + teamMates).distinctBy { player -> player.id })
        }
        logger.d { "Player updated!" }
        return@withContext
    }

    override fun getPlayer(id: Int): Flow<Player> = getPlayerList().map { list -> list.first { it.id == id } }

    override fun getPlayerList(): Flow<List<Player>> =
        playersStateFlow
}
