package io.imrekaszab.eaplayers.theme

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Shape

@Immutable
data class AppShapes(
    private val appDimens: AppDimens,
    val default: Default = Default(dimens = appDimens),
) {
    data class Default internal constructor(
        private val dimens: AppDimens,
        val circle: Shape = CircleShape,
        val roundedBig: Shape = RoundedCornerShape(dimens.margin.big),
        val roundedDefault: Shape = RoundedCornerShape(dimens.margin.default),
        val roundedSmall: Shape = RoundedCornerShape(dimens.margin.small),
        val roundedTiny: Shape = RoundedCornerShape(dimens.margin.tiny),
        val roundedRoomy: Shape = RoundedCornerShape(dimens.margin.roomy)
    )
}

internal val LocalAppShapes = staticCompositionLocalOf<AppShapes> { error("No AppShapes provided") }
