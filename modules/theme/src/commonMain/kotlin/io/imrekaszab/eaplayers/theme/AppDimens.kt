package io.imrekaszab.eaplayers.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.ui.tooling.preview.Preview

@Immutable
data class AppDimens internal constructor(
    val margin: Margin = Margin(),
    val fontSize: FontSize = FontSize(),
    val lineHeight: LineHeight = LineHeight(),
    val bottomNavigation: BottomNavigation = BottomNavigation(),
    val divider: Divider = Divider(),
    val drawerMenu: DrawerMenu = DrawerMenu(),
    val textInput: TextInput = TextInput(),
    val imageSize: ImageSize = ImageSize(),
    val playerDetailView: PlayerDetailView = PlayerDetailView()
) {
    data class Margin internal constructor(
        val huge: Dp = 56.dp,
        val extraLarge: Dp = 48.dp,
        val larger: Dp = 44.dp,
        val large: Dp = 40.dp,
        val bigger: Dp = 32.dp,
        val bigRoomy: Dp = 28.dp,
        val big: Dp = 24.dp,
        val roomy: Dp = 20.dp,
        val default: Dp = 16.dp,
        val small: Dp = 12.dp,
        val smaller: Dp = 10.dp,
        val tiny: Dp = 8.dp,
        val extraTiny: Dp = 4.dp,
        val tiniest: Dp = 2.dp
    )

    data class FontSize internal constructor(
        val headerL: TextUnit = 28.sp,
        val headerM: TextUnit = 22.sp,
        val headerS: TextUnit = 18.sp,
        val headerXS: TextUnit = 18.sp,
        val bodyL: TextUnit = 18.sp,
        val bodyM: TextUnit = 16.sp,
        val bodyS: TextUnit = 14.sp,
        val bodyXS: TextUnit = 12.sp,
        val buttonL: TextUnit = 20.sp,
        val buttonM: TextUnit = 18.sp,
        val buttonS: TextUnit = 16.sp,
        val buttonT: TextUnit = 14.sp
    )

    data class LineHeight internal constructor(
        val headerL: TextUnit = 34.sp,
        val headerM: TextUnit = 28.sp,
        val headerS: TextUnit = 23.sp,
        val bodyL: TextUnit = 23.sp,
        val buttonL: TextUnit = 50.sp,
        val buttonM: TextUnit = 38.sp,
        val buttonS: TextUnit = 32.sp,
        val buttonT: TextUnit = 24.sp
    )

    data class Divider internal constructor(
        val thickness: Dp = 1.dp
    )

    data class BottomNavigation internal constructor(
        val shadowRadius: Dp = 24.dp,
        val menuElevation: Dp = 3.dp
    )

    data class DrawerMenu internal constructor(
        val dividerStartPadding: Dp = 60.dp
    )

    data class TextInput internal constructor(
        val textInputMinHeight: Dp = 60.dp,
        val textInputHeight: Dp = 104.dp,
        val longTextInputHeight: Dp = 104.dp
    )

    data class ImageSize internal constructor(
        val abilityItemImageSize: Dp = 40.dp,
        val playerStatItemIconSize: Dp = 32.dp,
    )

    data class PlayerDetailView internal constructor(
        val boxSize: Dp = 400.dp,
        val imageSize: Dp = 50.dp,
        val cardWidth: Dp = 80.dp,
        val gridMinSize: Dp = 100.dp,
        val playerItemImageSize: Dp = 100.dp
    )
}

internal val LocalAppDimens = staticCompositionLocalOf { AppDimens() }

@Preview
@Composable
private fun AppDimensPreview() {
    AppTheme {
        Column {
            Box(
                Modifier
                    .padding(AppTheme.dimens.margin.small)
                    .size(20.dp)
                    .background(Color.Magenta)
            )
        }
    }
}
