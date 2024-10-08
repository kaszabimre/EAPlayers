package io.imrekaszab.eaplayers.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import coil3.compose.AsyncImage
import eaplayers.features.details.view.generated.resources.Res
import eaplayers.features.details.view.generated.resources.player_rank
import io.imrekaszab.eaplayers.domain.model.Player
import io.imrekaszab.eaplayers.theme.AppTheme
import org.jetbrains.compose.resources.stringResource

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
                Res.string.player_rank,
                player.firstName,
                player.lastName
            ),
            modifier = Modifier.fillMaxSize()
        )
    }
}
