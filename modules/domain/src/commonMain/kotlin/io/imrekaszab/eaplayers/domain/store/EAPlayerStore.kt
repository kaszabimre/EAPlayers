package io.imrekaszab.eaplayers.domain.store

import io.imrekaszab.eaplayers.domain.model.Player
import kotlinx.coroutines.flow.Flow

interface EAPlayerStore {
    fun getPlayer(id: Int): Flow<Player>
    fun getPlayerList(): Flow<List<Player>>
}
