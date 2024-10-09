package io.imrekaszab.eaplayers.data.model

import kotlinx.serialization.Serializable

@Serializable
data class PlayersResponse(
    val items: List<PlayerApiModel>,
    val totalItems: Int
)
