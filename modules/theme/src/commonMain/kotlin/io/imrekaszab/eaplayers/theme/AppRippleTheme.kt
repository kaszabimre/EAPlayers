package io.imrekaszab.eaplayers.theme

import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RippleConfiguration
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

object AppRippleTheme {
    private val rippleAlpha = RippleAlpha(
        pressedAlpha = 0.25f,
        focusedAlpha = 0.25f,
        draggedAlpha = 0.25f,
        hoveredAlpha = 0.25f
    )

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun getAppRippleTheme() = RippleConfiguration(
        color = AppTheme.colors.blue.default,
        rippleAlpha = rippleAlpha
    )

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun getColoredRippleConfiguration(rippleColor: Color) = RippleConfiguration(
        color = rippleColor,
        rippleAlpha = rippleAlpha
    )
}
