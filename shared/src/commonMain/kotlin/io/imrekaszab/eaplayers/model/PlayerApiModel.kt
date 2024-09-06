package io.imrekaszab.eaplayers.model

import kotlinx.serialization.Serializable

@Serializable
data class PlayerApiModel(
    val id: Int,
    val rank: Int,
    val overallRating: Int,
    val firstName: String,
    val lastName: String,
    val commonName: String? = null,
    val birthdate: String,
    val height: Int,
    val skillMoves: Int,
    val weakFootAbility: Int,
    val attackingWorkRate: Int,
    val defensiveWorkRate: Int,
    val avatarUrl: String? = null,
    val shieldUrl: String? = null,
    val nationality: NationalityApiModel,
    val team: TeamApiModel,
    val position: PositionApiModel,
    val playerAbilities: List<PlayerAbilityApiModel>
)

@Serializable
data class NationalityApiModel(
    val id: Int,
    val label: String,
    val imageUrl: String
)

@Serializable
data class TeamApiModel(
    val id: Int,
    val label: String,
    val imageUrl: String,
    val isPopular: Boolean
)

@Serializable
data class PositionApiModel(
    val id: String,
    val shortLabel: String,
    val label: String
)

@Serializable
data class PlayerAbilityApiModel(
    val id: String,
    val label: String,
    val description: String,
    val imageUrl: String,
    val type: AbilityTypeApiModel
)

@Serializable
data class AbilityTypeApiModel(
    val id: String,
    val label: String
)
