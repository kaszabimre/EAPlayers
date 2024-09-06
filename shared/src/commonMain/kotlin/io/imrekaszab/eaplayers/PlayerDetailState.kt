package io.imrekaszab.eaplayers

import io.imrekaszab.eaplayers.domain.model.Player

data class PlayerDetailState(
    val player: Player? = null,
    val error: String? = null,
    val loading: Boolean = false,
)
