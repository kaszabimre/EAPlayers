package io.imrekaszab.eaplayers.ui.model

import io.imrekaszab.eaplayers.domain.model.Player

data class PlayerListState(
    val textFieldValue: String = "",
    val players: List<Player> = emptyList(),
    val error: String? = null,
    val loading: Boolean = false,
    val selectedPlayer: Player? = null,
)
