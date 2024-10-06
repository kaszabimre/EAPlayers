package widget

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import coil3.compose.AsyncImage
import io.imrekaszab.eaplayers.theme.AppTheme

@Composable
fun PlayerStatItem(imageUrl: String, stat: String, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        AsyncImage(
            model = imageUrl,
            contentDescription = label,
            modifier = Modifier.size(AppTheme.dimens.imageSize.playerStatItemIconSize)
        )
        Spacer(modifier = Modifier.height(AppTheme.dimens.margin.tiny))
        Text(
            text = stat,
            style = AppTheme.typography.body.large,
            color = AppTheme.colors.yellow.default
        )
        Text(
            text = label,
            style = AppTheme.typography.body.small,
            color = AppTheme.colors.yellow.light1
        )
    }
}

@Composable
fun PlayerStatItem(icon: ImageVector, stat: String, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = AppTheme.colorScheme.primary,
            modifier = Modifier.size(AppTheme.dimens.imageSize.playerStatItemIconSize)
        )
        Spacer(modifier = Modifier.height(AppTheme.dimens.margin.tiny))
        Text(
            text = stat,
            style = AppTheme.typography.body.large,
            color = AppTheme.colorScheme.onSurface
        )
        Text(
            text = label,
            style = AppTheme.typography.body.small,
            color = AppTheme.colorScheme.onSurfaceVariant
        )
    }
}
