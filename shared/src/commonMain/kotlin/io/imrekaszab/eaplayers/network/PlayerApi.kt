package io.imrekaszab.eaplayers.network

import io.imrekaszab.eaplayers.model.PlayersResponse

interface PlayerApi {
    suspend fun getPlayersResponse(search: String): PlayersResponse?
    suspend fun getPlayersResponseByTeam(teamId: Int): PlayersResponse?
}
