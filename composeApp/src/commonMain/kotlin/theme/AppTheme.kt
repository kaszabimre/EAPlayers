package theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalRippleConfiguration
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTheme(
    dimens: AppDimens = AppTheme.dimens,
    shapes: AppShapes = AppShapes(AppTheme.dimens),
    darkTheme: Boolean = false,  // Flag to toggle between light and dark themes
    colors: AppColors = AppColors(),
    colorScheme: ColorScheme = getColorScheme(darkTheme = darkTheme),
    typography: AppTypography = AppTypography(
        AppTheme.dimens,
        colorScheme,
        fontFamily = getDefaultFontFamily()
    ),
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalAppColors provides colors,
        LocalAppDimens provides dimens,
        LocalAppTypography provides typography,
        LocalAppShapes provides shapes,
        LocalRippleConfiguration provides AppRippleTheme.getAppRippleTheme(),
    ) {
        MaterialTheme(colorScheme = debugColors()) {
            CompositionLocalProvider(
                LocalTextStyle provides AppTheme.typography.body.medium,
                LocalRippleConfiguration provides AppRippleTheme.getAppRippleTheme(),
                LocalAppShapes provides AppShapes(dimens),
                LocalTextSelectionColors provides appTextSelectionColors()
            ) {
                content()
            }
        }
    }
}

object AppTheme {
    val colors: AppColors
        @Composable
        @ReadOnlyComposable
        get() = LocalAppColors.current

    val dimens: AppDimens
        @Composable
        @ReadOnlyComposable
        get() = LocalAppDimens.current

    val typography: AppTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalAppTypography.current

    val colorScheme: ColorScheme
      @Composable
      @ReadOnlyComposable
      get() = getColorScheme(isSystemInDarkTheme())

    val shapes: AppShapes
        @Composable
        @ReadOnlyComposable
        get() = LocalAppShapes.current
}
