package io.imrekaszab.eaplayers.theme.preview

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.imrekaszab.eaplayers.theme.AppTheme

@Composable
fun PreviewBox(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    AppTheme {
        Box(modifier = modifier.background(color = AppTheme.colorScheme.inverseOnSurface)) {
            content()
        }
    }
}
