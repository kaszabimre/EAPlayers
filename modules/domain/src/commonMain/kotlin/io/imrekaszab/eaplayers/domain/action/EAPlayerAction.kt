package io.imrekaszab.eaplayers.domain.action

import io.imrekaszab.eaplayers.domain.model.Player

interface EAPlayerAction {
    suspend fun refreshPlayers(search: String)
    suspend fun selectPlayer(player: Player)
}
