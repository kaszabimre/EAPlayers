package io.imrekaszab.eaplayers.data.api

import io.imrekaszab.eaplayers.data.model.PlayersResponse

interface PlayerApi {
    suspend fun getPlayersResponse(search: String): PlayersResponse?
    suspend fun getPlayersResponseByTeam(teamId: Int): PlayersResponse?
}
