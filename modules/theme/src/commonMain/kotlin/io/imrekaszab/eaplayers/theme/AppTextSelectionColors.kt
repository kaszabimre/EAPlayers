package io.imrekaszab.eaplayers.theme

import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.runtime.Composable

@Composable
internal fun appTextSelectionColors() = TextSelectionColors(
    handleColor = AppTheme.colors.blue.default,
    backgroundColor = AppTheme.colors.blue.default.copy(alpha = 0.4F)
)
