import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.text.font.FontWeight
import coil3.compose.AsyncImage
import io.imrekaszab.eaplayers.domain.model.Player
import theme.AppTheme

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
            .clickable {
                onPlayerClick(player)
            }
    ) {
        Row(
            modifier = Modifier
                .background(AppTheme.colorScheme.surface)
                .padding(AppTheme.dimens.margin.default),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = player.avatarUrl,
                contentDescription = "${player.firstName} ${player.lastName}",
                modifier = Modifier
                    .size(AppTheme.dimens.playerDetailView.playerItemImageSize)
                    .padding(end = AppTheme.dimens.margin.default),
            )

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = player.commonName ?: "${player.firstName} ${player.lastName}",
                    style = AppTheme.typography.heading.medium.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    color = AppTheme.colorScheme.onSurface
                )
                Text(
                    text = "Rating: ${player.overallRating}",
                    style = AppTheme.typography.body.medium,
                    color = AppTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = "Position: ${player.position.label}",
                    style = AppTheme.typography.body.small,
                    color = AppTheme.colorScheme.secondary
                )
            }

            Surface(
                shape = AppTheme.shapes.default.roundedDefault,
                color = AppTheme.colorScheme.primary,
                modifier = Modifier.size(AppTheme.dimens.margin.extraLarge)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Rank",
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
    }
}
