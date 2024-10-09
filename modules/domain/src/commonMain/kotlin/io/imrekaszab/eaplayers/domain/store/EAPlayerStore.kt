package io.imrekaszab.eaplayers.domain.store

import io.imrekaszab.eaplayers.domain.model.Player
import kotlinx.coroutines.flow.Flow

interface EAPlayerStore {
    fun getPlayerList(): Flow<List<Player>>
}
