package io.imrekaszab.eaplayers.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import eaplayers.features.details.view.generated.resources.Res
import eaplayers.features.details.view.generated.resources.abilities
import eaplayers.features.details.view.generated.resources.nationality
import eaplayers.features.details.view.generated.resources.player_rank
import eaplayers.features.details.view.generated.resources.team
import io.imrekaszab.eaplayers.domain.model.Player
import io.imrekaszab.eaplayers.theme.AppTheme
import org.jetbrains.compose.resources.stringResource

@Composable
fun PlayerDetailView(
    modifier: Modifier = Modifier,
    player: Player,
    onTeamMateSelected: (Player) -> Unit,
    isMainStatsExpanded: Boolean,
    isOtherStatsExpanded: Boolean,
    onMainStatsExpandClick: () -> Unit,
    onOtherStatsExpandClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = AppTheme.dimens.margin.default)
            .background(AppTheme.colorScheme.background)
    ) {
        PlayerImage(player = player)

        PlayerInfoSection(player = player)

        Spacer(modifier = Modifier.height(AppTheme.dimens.margin.default))

        PlayerStatsRow(player = player)

        Spacer(modifier = Modifier.height(AppTheme.dimens.margin.big))

        StatsView(
            stats = player.stats,
            isMainStatsExpanded = isMainStatsExpanded,
            isOtherStatsExpanded = isOtherStatsExpanded,
            onMainStatsExpandClick = onMainStatsExpandClick,
            onOtherStatsExpandClick = onOtherStatsExpandClick
        )

        Spacer(modifier = Modifier.height(AppTheme.dimens.margin.big))

        AbilitiesSection(player = player)

        Spacer(modifier = Modifier.height(AppTheme.dimens.margin.big))

        TeamMatesSection(
            player = player,
            onTeamMateSelected = onTeamMateSelected
        )
    }
}

@Composable
fun PlayerInfoSection(player: Player) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "${player.firstName} ${player.lastName}",
            style = AppTheme.typography.heading.large,
            color = AppTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center
        )
        Text(
            text = stringResource(Res.string.player_rank, player.rank),
            style = AppTheme.typography.heading.large,
            color = AppTheme.colorScheme.secondary
        )

        Spacer(modifier = Modifier.height(AppTheme.dimens.margin.default))

        PlayerStatRow(
            statItems = listOf(
                PlayerStat(
                    imageUrl = player.nationality.imageUrl,
                    stat = player.nationality.label,
                    label = stringResource(Res.string.nationality)
                ),
                PlayerStat(
                    imageUrl = player.team.imageUrl,
                    stat = player.team.label,
                    label = stringResource(Res.string.team)
                )
            )
        )
    }
}

@Composable
fun AbilitiesSection(player: Player) {
    if (player.playerAbilities.isNotEmpty()) {
        Column(
            modifier = Modifier
                .clip(AppTheme.shapes.default.roundedDefault)
                .background(
                    color = AppTheme.colors.blue.default,
                    shape = AppTheme.shapes.default.roundedDefault
                )
                .padding(AppTheme.dimens.margin.default)
        ) {
            Text(
                text = stringResource(Res.string.abilities),
                style = AppTheme.typography.heading.medium,
                color = AppTheme.colors.yellow.default
            )
            Spacer(modifier = Modifier.height(AppTheme.dimens.margin.tiny))

            player.playerAbilities.forEach { ability ->
                AbilityItemView(ability)
            }
        }
    }
}
