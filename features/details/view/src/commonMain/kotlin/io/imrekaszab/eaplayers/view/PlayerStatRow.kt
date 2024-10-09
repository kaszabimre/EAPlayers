package io.imrekaszab.eaplayers.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import eaplayers.features.details.view.generated.resources.Res
import eaplayers.features.details.view.generated.resources.height
import eaplayers.features.details.view.generated.resources.position
import eaplayers.features.details.view.generated.resources.rating
import io.imrekaszab.eaplayers.domain.model.Player
import io.imrekaszab.eaplayers.theme.AppTheme
import org.jetbrains.compose.resources.stringResource

@Composable
fun PlayerStatsRow(player: Player) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = AppTheme.dimens.margin.default),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        PlayerStatItem(
            icon = Icons.Default.Star,
            stat = player.overallRating.toString(),
            label = stringResource(Res.string.rating)
        )
        PlayerStatItem(
            icon = Icons.Default.Menu,
            stat = player.position.shortLabel,
            label = stringResource(Res.string.position)
        )
        PlayerStatItem(
            icon = Icons.Default.KeyboardArrowUp,
            stat = "${player.height} cm",
            label = stringResource(Res.string.height)
        )
    }
}

@Composable
fun PlayerStatRow(statItems: List<PlayerStat>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(AppTheme.shapes.default.roundedDefault)
            .background(
                color = AppTheme.colors.blue.default,
                shape = AppTheme.shapes.default.roundedDefault
            )
            .padding(AppTheme.dimens.margin.default),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        statItems.forEach { statItem ->
            PlayerStatItem(
                imageUrl = statItem.imageUrl,
                stat = statItem.stat,
                label = statItem.label
            )
        }
    }
}

data class PlayerStat(
    val imageUrl: String,
    val stat: String,
    val label: String
)
