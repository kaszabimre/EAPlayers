package io.imrekaszab.eaplayers.domain.model

data class Player(
    val id: Int,
    val rank: Int,
    val overallRating: Int,
    val firstName: String,
    val lastName: String,
    val commonName: String?,
    val birthdate: String,
    val height: Int,
    val skillMoves: Int,
    val weakFootAbility: Int,
    val attackingWorkRate: Int,
    val defensiveWorkRate: Int,
    val avatarUrl: String?, // New field
    val shieldUrl: String?, // New field
    val nationality: Nationality,
    val team: Team,
    val position: Position,
    val playerAbilities: List<PlayerAbility>,
    val teamMates: List<Player> = emptyList(),
    val stats: List<StatEntry> = emptyList()
)

data class Nationality(
    val id: Int,
    val label: String,
    val imageUrl: String
)

data class Team(
    val id: Int,
    val label: String,
    val imageUrl: String,
    val isPopular: Boolean
)

data class Position(
    val id: String,
    val shortLabel: String,
    val label: String
)

data class PlayerAbility(
    val id: String,
    val label: String,
    val description: String,
    val imageUrl: String,
    val type: AbilityType
)

data class AbilityType(
    val id: String,
    val label: String
)

data class StatEntry(
    val displayName: String,
    val value: Int,
    val diff: Int,
    val isAnimated: Boolean = false
)
