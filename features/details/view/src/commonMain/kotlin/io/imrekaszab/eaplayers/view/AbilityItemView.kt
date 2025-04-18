package io.imrekaszab.eaplayers.view

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import coil3.compose.AsyncImage
import io.imrekaszab.eaplayers.domain.model.PlayerAbility
import io.imrekaszab.eaplayers.theme.AppTheme
import io.imrekaszab.eaplayers.theme.util.EAImageLoader

@Composable
fun AbilityItemView(ability: PlayerAbility) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = AppTheme.dimens.margin.tiny),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            modifier = Modifier.size(AppTheme.dimens.imageSize.abilityItemImageSize),
            model = ability.imageUrl,
            imageLoader = EAImageLoader(),
            contentDescription = ability.label
        )
        Spacer(modifier = Modifier.width(AppTheme.dimens.margin.default))
        Text(
            text = ability.label,
            style = AppTheme.typography.body.large.copy(fontWeight = FontWeight.Bold),
            color = AppTheme.colors.yellow.light2
        )
    }
}
