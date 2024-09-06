package io.imrekaszab.eaplayers.core.util

import androidx.annotation.FloatRange

/**
 * Linearly interpolate between two values
 */
fun lerp(
    startValue: Float,
    endValue: Float,
    @FloatRange(from = 0.0, to = 1.0) fraction: Float
) = startValue + fraction * (endValue - startValue)
