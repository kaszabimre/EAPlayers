package io.imrekaszab.eaplayers.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import coil3.compose.AsyncImage
import eaplayers.features.list.view.generated.resources.Res
import eaplayers.features.list.view.generated.resources.player_image_desc
import eaplayers.features.list.view.generated.resources.position_with_param
import eaplayers.features.list.view.generated.resources.rank
import eaplayers.features.list.view.generated.resources.rating_with_param
import io.imrekaszab.eaplayers.domain.model.Player
import io.imrekaszab.eaplayers.theme.AppTheme
import org.jetbrains.compose.resources.stringResource

@Composable
fun PlayerItemView(player: Player, onPlayerClick: (Player) -> Unit) {
    Card(
        shape = AppTheme.shapes.default.roundedSmall,
        colors = CardDefaults.cardColors(
            containerColor = AppTheme.colorScheme.surfaceVariant
        ),
        modifier = Modifier
            .padding(AppTheme.dimens.margin.small)
            .fillMaxWidth()
            .clip(AppTheme.shapes.default.roundedSmall)
            .clickable { onPlayerClick(player) }
    ) {
        Row(
            modifier = Modifier
                .background(AppTheme.colorScheme.surface)
                .padding(AppTheme.dimens.margin.default),
            verticalAlignment = Alignment.CenterVertically
        ) {
            PlayerDetailImage(player = player)
            PlayerInfo(modifier = Modifier.weight(1f), player = player)
            PlayerRankBadge(player = player)
        }
    }
}

@Composable
fun PlayerDetailImage(player: Player) {
    AsyncImage(
        model = player.avatarUrl,
        contentDescription = stringResource(
            Res.string.player_image_desc,
            player.firstName,
            player.lastName
        ),
        modifier = Modifier
            .size(AppTheme.dimens.playerDetailView.playerItemImageSize)
            .padding(end = AppTheme.dimens.margin.default)
    )
}

@Composable
fun PlayerInfo(modifier: Modifier, player: Player) {
    Column(modifier = modifier) {
        Text(
            text = player.commonName ?: "${player.firstName} ${player.lastName}",
            style = AppTheme.typography.heading.medium.copy(
                fontWeight = FontWeight.Bold
            ),
            color = AppTheme.colorScheme.onSurface
        )
        Text(
            text = stringResource(Res.string.rating_with_param, player.overallRating),
            style = AppTheme.typography.body.medium,
            color = AppTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = stringResource(Res.string.position_with_param, player.position.label),
            style = AppTheme.typography.body.small,
            color = AppTheme.colorScheme.secondary
        )
    }
}

@Composable
fun PlayerRankBadge(player: Player) {
    Surface(
        shape = AppTheme.shapes.default.roundedDefault,
        color = AppTheme.colorScheme.primary,
        modifier = Modifier.defaultMinSize(AppTheme.dimens.margin.extraLarge)
    ) {
        Column(
            modifier = Modifier.padding(AppTheme.dimens.margin.extraTiny),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(Res.string.rank),
                style = AppTheme.typography.body.extraSmall,
                color = AppTheme.colors.blackAndWhite.white,
            )
            Text(
                text = player.rank.toString(),
                color = AppTheme.colors.blackAndWhite.white,
                style = AppTheme.typography.body.large
            )
        }
    }
}
