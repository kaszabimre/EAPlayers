package io.imrekaszab.eaplayers.data.mapper

import io.imrekaszab.eaplayers.core.util.displayNameToCamelCase
import io.imrekaszab.eaplayers.core.util.splitCamelCase
import io.imrekaszab.eaplayers.core.util.uppercaseIfThreeLetters
import io.imrekaszab.eaplayers.data.model.AbilityTypeApiModel
import io.imrekaszab.eaplayers.data.model.NationalityApiModel
import io.imrekaszab.eaplayers.data.model.PlayerAbilityApiModel
import io.imrekaszab.eaplayers.data.model.PlayerApiModel
import io.imrekaszab.eaplayers.data.model.PositionApiModel
import io.imrekaszab.eaplayers.data.model.StatDetailApiModel
import io.imrekaszab.eaplayers.data.model.TeamApiModel
import io.imrekaszab.eaplayers.domain.model.AbilityType
import io.imrekaszab.eaplayers.domain.model.Nationality
import io.imrekaszab.eaplayers.domain.model.Player
import io.imrekaszab.eaplayers.domain.model.PlayerAbility
import io.imrekaszab.eaplayers.domain.model.Position
import io.imrekaszab.eaplayers.domain.model.StatEntry
import io.imrekaszab.eaplayers.domain.model.Team

fun PlayerApiModel.toPlayer() = Player(
    id = this.id,
    rank = this.rank,
    overallRating = this.overallRating,
    firstName = this.firstName,
    lastName = this.lastName,
    commonName = this.commonName,
    birthdate = this.birthdate,
    height = this.height,
    skillMoves = this.skillMoves,
    weakFootAbility = this.weakFootAbility,
    attackingWorkRate = this.attackingWorkRate ?: 0,
    defensiveWorkRate = this.defensiveWorkRate ?: 0,
    avatarUrl = this.avatarUrl,
    shieldUrl = this.shieldUrl,
    nationality = this.nationality.toDomain(),
    team = this.team.toDomain(),
    position = this.position.toDomain(),
    playerAbilities = this.playerAbilities.map { it.toDomain() },
    stats = this.stats.toDomainStatsList(),
)

fun Player.toApiModel() = PlayerApiModel(
    id = this.id,
    rank = this.rank,
    overallRating = this.overallRating,
    firstName = this.firstName,
    lastName = this.lastName,
    commonName = this.commonName,
    birthdate = this.birthdate,
    height = this.height,
    skillMoves = this.skillMoves,
    weakFootAbility = this.weakFootAbility,
    attackingWorkRate = this.attackingWorkRate,
    defensiveWorkRate = this.defensiveWorkRate,
    avatarUrl = this.avatarUrl,
    shieldUrl = this.shieldUrl,
    nationality = this.nationality.toApiModel(),
    team = this.team.toApiModel(),
    position = this.position.toApiModel(),
    playerAbilities = this.playerAbilities.map { it.toApiModel() },
    stats = this.stats.toApiStatsMap()
)

// Extension functions for nested data classes

fun NationalityApiModel.toDomain() = Nationality(id, label, imageUrl)
fun TeamApiModel.toDomain() = Team(id, label, imageUrl, isPopular)
fun PositionApiModel.toDomain() = Position(id, shortLabel, label)
fun PlayerAbilityApiModel.toDomain() = PlayerAbility(id, label, description, imageUrl, type.toDomain())

fun Nationality.toApiModel() = NationalityApiModel(id, label, imageUrl)
fun Team.toApiModel() = TeamApiModel(id, label, imageUrl, isPopular)
fun Position.toApiModel() = PositionApiModel(id, shortLabel, label)
fun PlayerAbility.toApiModel() = PlayerAbilityApiModel(id, label, description, imageUrl, type.toApiModel())

fun AbilityTypeApiModel.toDomain() = AbilityType(id, label)
fun AbilityType.toApiModel() = AbilityTypeApiModel(id, label)

fun List<StatEntry>.toApiStatsMap(): Map<String, StatDetailApiModel> = this.associate { statEntry ->
    statEntry.displayName.displayNameToCamelCase() to statEntry.toApiModel()
}

fun StatEntry.toApiModel(): StatDetailApiModel = StatDetailApiModel(value = this.value, diff = this.diff)

private val MAIN_SKILLS_ORDER = listOf("PAC", "SHO", "PAS", "DRI", "DEF", "PHY")
fun Map<String, StatDetailApiModel>.toDomainStatsList(): List<StatEntry> = this.map { (key, statDetail) ->
    StatEntry(
        displayName = key.splitCamelCase().uppercaseIfThreeLetters(),
        value = statDetail.value,
        diff = statDetail.diff
    )
}.sortedBy { entry ->
    MAIN_SKILLS_ORDER.indexOf(entry.displayName).takeIf { it >= 0 } ?: Int.MAX_VALUE
}
