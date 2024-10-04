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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import io.imrekaszab.eaplayers.domain.model.Player
import theme.AppTheme
import widget.AbilityItemView
import widget.PlayerStatItem

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
            .padding(horizontal = 16.dp)
            .background(AppTheme.colorScheme.background)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
                .aspectRatio(1f)
                .clip(RoundedCornerShape(16.dp))
                .background(AppTheme.colorScheme.surface)
        ) {
            AsyncImage(
                model = player.shieldUrl,
                contentDescription = "${player.firstName} ${player.lastName}",
                modifier = Modifier.fillMaxSize(),
            )
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "${player.firstName} ${player.lastName}",
                style = AppTheme.typography.heading.large.copy(fontWeight = FontWeight.Bold),
                color = AppTheme.colorScheme.onBackground
            )
            Text(
                text = "Rank: ${player.rank}",
                style = AppTheme.typography.heading.large.copy(fontSize = 18.sp),
                color = AppTheme.colorScheme.secondary
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {
                PlayerStatItem(
                    imageUrl = player.nationality.imageUrl,
                    stat = player.nationality.label,
                    label = "Nationality"
                )
                PlayerStatItem(
                    imageUrl = player.team.imageUrl,
                    stat = player.team.label,
                    label = "Team"
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            PlayerStatItem(
                icon = Icons.Default.Star,
                stat = player.overallRating.toString(),
                label = "Rating"
            )
            PlayerStatItem(
                icon = Icons.Default.Menu,
                stat = player.position.shortLabel,
                label = "Position"
            )
            PlayerStatItem(
                icon = Icons.Default.CheckCircle,
                stat = "${player.height} cm",
                label = "Height"
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        if (player.playerAbilities.isNotEmpty()) {
            Text(
                text = "Abilities",
                style = AppTheme.typography.heading.medium,
                color = AppTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.height(8.dp))

            player.playerAbilities.forEach { ability ->
                AbilityItemView(ability)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Team mates",
            style = AppTheme.typography.heading.medium,
            color = AppTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(8.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
        ) {
            LazyVerticalGrid(columns = GridCells.Adaptive(minSize = 100.dp)) {
                items(
                    player.teamMates.filter { it.id != player.id }
                        .sortedByDescending { it.overallRating }
                ) { mate ->
                    Column(
                        modifier = Modifier
                            .padding(8.dp)
                            .width(width = 80.dp)
                            .clickable {
                                onTeamMateSelected(mate)
                            },
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        AsyncImage(
                            modifier = Modifier
                                .size(50.dp)
                                .clip(CircleShape),
                            model = mate.avatarUrl,
                            contentDescription = mate.lastName
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = mate.commonName
                                ?: (mate.firstName + ". " + mate.lastName.first()),
                            textAlign = TextAlign.Center,
                            style = AppTheme.typography.body.small.copy(
                                fontWeight = FontWeight.Bold
                            ),
                            color = AppTheme.colorScheme.onSurface
                        )
                        Text(
                            text = mate.position.shortLabel,
                            style = AppTheme.typography.body.small,
                            color = AppTheme.colorScheme.onSurface
                        )
                    }
                }
            }
        }
    }
}
