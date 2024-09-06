package io.imrekaszab.eaplayers.model

import kotlinx.serialization.Serializable

@Serializable
data class PlayersResponse(
    val items: List<PlayerApiModel>,
    val totalItems: Int
)
