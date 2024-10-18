package io.imrekaszab.eaplayers.theme.widgets

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import io.imrekaszab.eaplayers.theme.AppTheme

private const val FIRST_LIMIT = 33
private const val SECOND_LIMIT = 66
private const val ANIMATION_DURATION = 1000

@Composable
fun AnimatedLinearProgressIndicator(value: Int, isAnimated: Boolean) {
    var animate by remember { mutableStateOf(isAnimated) }

    val animatedProgress by animateFloatAsState(
        targetValue = if (animate) value / 100f else 0f,
        animationSpec = tween(durationMillis = ANIMATION_DURATION)
    )

    LaunchedEffect(Unit) {
        animate = true
    }
    LinearProgressIndicator(
        progress = { animatedProgress },
        color = getProgressBarColor(value),
        trackColor = AppTheme.colors.blue.light2,
        drawStopIndicator = {},
        gapSize = 0.dp,
        strokeCap = StrokeCap.Round,
        modifier = Modifier
            .height(AppTheme.dimens.margin.small)
            .fillMaxWidth()
            .background(color = AppTheme.colors.blue.light2, shape = AppTheme.shapes.default.roundedSmall)
    )
}

@Composable
fun AnimatedCircularProgressIndicator(value: Int, isAnimated: Boolean) {
    var animate by remember { mutableStateOf(isAnimated) }

    val animatedProgress by animateFloatAsState(
        targetValue = if (animate) value / 100f else 0f,
        animationSpec = tween(durationMillis = ANIMATION_DURATION)
    )

    LaunchedEffect(Unit) {
        animate = true
    }

    CircularProgressIndicator(
        progress = { animatedProgress },
        modifier = Modifier
            .size(AppTheme.dimens.statItem.circleSize)
            .clip(AppTheme.shapes.default.circle),
        color = getProgressBarColor(value),
        strokeWidth = AppTheme.dimens.margin.smaller,
        trackColor = AppTheme.colors.blue.light2,
        strokeCap = StrokeCap.Butt,
        gapSize = 0.dp
    )
}

@Composable
fun getProgressBarColor(value: Int): Color = when {
    value <= FIRST_LIMIT -> AppTheme.colors.red.default
    value <= SECOND_LIMIT -> AppTheme.colors.yellow.default
    else -> AppTheme.colors.blue.default
}
