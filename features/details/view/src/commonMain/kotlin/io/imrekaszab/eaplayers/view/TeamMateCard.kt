package io.imrekaszab.eaplayers.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import coil3.compose.AsyncImage
import eaplayers.features.details.view.generated.resources.Res
import eaplayers.features.details.view.generated.resources.team_mates
import io.imrekaszab.eaplayers.domain.model.Player
import io.imrekaszab.eaplayers.theme.AppTheme
import org.jetbrains.compose.resources.stringResource

@Composable
fun TeamMatesSection(player: Player, onTeamMateSelected: (Player) -> Unit) {
    Text(
        text = stringResource(Res.string.team_mates),
        style = AppTheme.typography.heading.medium,
        color = AppTheme.colorScheme.onBackground
    )
    Spacer(modifier = Modifier.height(AppTheme.dimens.margin.tiny))

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(AppTheme.dimens.playerDetailView.boxSize)
    ) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = AppTheme.dimens.playerDetailView.gridMinSize)
        ) {
            items(
                player.teamMates.filter { it.id != player.id }
                    .sortedByDescending { it.overallRating }
            ) { mate ->
                TeamMateCard(mate = mate, onTeamMateSelected = onTeamMateSelected)
            }
        }
    }
}

@Composable
fun TeamMateCard(mate: Player, onTeamMateSelected: (Player) -> Unit) {
    Column(
        modifier = Modifier
            .padding(AppTheme.dimens.margin.tiny)
            .width(AppTheme.dimens.playerDetailView.cardWidth)
            .clip(AppTheme.shapes.default.roundedSmall)
            .clickable { onTeamMateSelected(mate) },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            modifier = Modifier
                .size(AppTheme.dimens.playerDetailView.imageSize)
                .clip(AppTheme.shapes.default.circle),
            model = mate.avatarUrl,
            contentDescription = mate.lastName
        )
        Spacer(modifier = Modifier.height(AppTheme.dimens.margin.tiny))
        Text(
            text = mate.commonName ?: "${mate.firstName}. ${mate.lastName.first()}",
            textAlign = TextAlign.Center,
            style = AppTheme.typography.body.small.copy(fontWeight = FontWeight.Bold),
            color = AppTheme.colorScheme.onSurface
        )
        Text(
            text = mate.position.shortLabel,
            style = AppTheme.typography.body.small,
            color = AppTheme.colorScheme.onSurface
        )
    }
}
