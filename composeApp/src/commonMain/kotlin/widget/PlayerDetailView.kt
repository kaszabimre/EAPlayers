package widget

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import coil3.compose.AsyncImage
import eaplayers.composeapp.generated.resources.Res
import eaplayers.composeapp.generated.resources.abilities
import eaplayers.composeapp.generated.resources.height
import eaplayers.composeapp.generated.resources.nationality
import eaplayers.composeapp.generated.resources.player_image_desc
import eaplayers.composeapp.generated.resources.player_rank
import eaplayers.composeapp.generated.resources.position
import eaplayers.composeapp.generated.resources.rating
import eaplayers.composeapp.generated.resources.team
import eaplayers.composeapp.generated.resources.team_mates
import io.imrekaszab.eaplayers.domain.model.Player
import org.jetbrains.compose.resources.stringResource
import theme.AppTheme

@Composable
fun PlayerDetailView(
    modifier: Modifier = Modifier,
    player: Player,
    onTeamMateSelected: (Player) -> Unit
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

        AbilitiesSection(player = player)

        Spacer(modifier = Modifier.height(AppTheme.dimens.margin.big))

        TeamMatesSection(
            player = player,
            onTeamMateSelected = onTeamMateSelected
        )
    }
}

@Composable
fun PlayerImage(player: Player) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = AppTheme.dimens.margin.default)
            .aspectRatio(1f)
            .clip(AppTheme.shapes.default.roundedDefault)
            .background(AppTheme.colorScheme.surface)
    ) {
        AsyncImage(
            model = player.shieldUrl,
            contentDescription = stringResource(
                Res.string.player_image_desc,
                player.firstName,
                player.lastName
            ),
            modifier = Modifier.fillMaxSize()
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
            color = AppTheme.colorScheme.onBackground
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
                    player.nationality.imageUrl,
                    player.nationality.label,
                    stringResource(Res.string.nationality)
                ),
                PlayerStat(player.team.imageUrl, player.team.label, stringResource(Res.string.team))
            )
        )
    }
}

@Composable
fun PlayerStatsRow(player: Player) {
    Row(
        modifier = Modifier.fillMaxWidth(),
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
            icon = Icons.Default.CheckCircle,
            stat = "${player.height} cm",
            label = stringResource(Res.string.height)
        )
    }
}

@Composable
fun AbilitiesSection(player: Player) {
    if (player.playerAbilities.isNotEmpty()) {
        Text(
            text = stringResource(Res.string.abilities),
            style = AppTheme.typography.heading.medium,
            color = AppTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(AppTheme.dimens.margin.tiny))

        player.playerAbilities.forEach { ability ->
            AbilityItemView(ability)
        }
    }
}

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

@Composable
fun PlayerStatRow(statItems: List<PlayerStat>) {
    Row(
        modifier = Modifier.fillMaxWidth(),
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
