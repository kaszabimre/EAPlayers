package io.imrekaszab.eaplayers.theme

import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Suppress("MagicNumber")
@Immutable
data class AppColors(
    val primary: Color = Blue().default, // Use Blue as Primary
    val onPrimary: Color = BlackAndWhite().white, // White on Blue
    val primaryContainer: Color = Blue().light1, // Lighter Blue
    val onPrimaryContainer: Color = BlackAndWhite().black, // Black on Light Blue
    val secondary: Color = Yellow().default, // Use Yellow as Secondary
    val onSecondary: Color = BlackAndWhite().black, // Black on Yellow
    val secondaryContainer: Color = Yellow().light2, // Light Yellow
    val onSecondaryContainer: Color = BlackAndWhite().black, // Black on Light Yellow
    val backgroundSurfaceColors: BackgroundSurfaceColors = BackgroundSurfaceColors(),
    val blackAndWhite: BlackAndWhite = BlackAndWhite(),
    val red: Red = Red(),
    val yellow: Yellow = Yellow(), // Yellow Color
    val blue: Blue = Blue(), // Blue Color
) {
    // Define Yellow color variations
    data class Yellow internal constructor(
        val default: Color = Color(0xFFFFC107), // Default Yellow (Secondary)
        val light1: Color = Color(0xFFFFD54F), // Lighter shade of Yellow
        val light2: Color = Color(0xFFFFECB3), // Lighter yellow variant
        val light3: Color = Color(0xFFFFF9C4) // Lightest shade of Yellow
    )

    // Define Blue color variations
    data class Blue internal constructor(
        val default: Color = Color(0xFF0D47A1), // Default Dark Blue (Primary)
        val light1: Color = Color(0xFF5472D3), // Lighter Blue (PrimaryContainer)
        val light2: Color = Color(0xFFBBDEFB), // Light Blue variant
        val light3: Color = Color(0xFFE3F2FD) // Lightest Blue
    )

    // Define Red color variations
    data class Red internal constructor(
        val default: Color = Color(0xFFEE4338),
        val light3: Color = Color(0xFFFFEFEE)
    )

    // Define Black and White color variations
    data class BlackAndWhite internal constructor(
        val black: Color = Color(0xFF000000),
        val white: Color = Color(0xFFFFFFFF)
    )

    // Define Background and Surface colors for both Light and Dark themes
    data class BackgroundSurfaceColors internal constructor(
        val lightBackground: Color = Color(0xFFF6F6F6), // Light Background
        val lightSurface: Color = Color.White, // Light Surface
        val darkBackground: Color = Color(0xFF121212), // Dark Background
        val darkSurface: Color = Color(0xFF1D1D1D) // Dark Surface
    )

    // BottomNavigation colors
    data class BottomNavigation internal constructor(
        val containerColor: Color = Color(0xFFFFFFFF),
        val containerShadowColor: Color = Color(0xFF000000),
        val defaultItem: ItemColor = ItemColor(contentColor = Color(0x80000000)),
        val selectedItem: ItemColor = ItemColor(contentColor = Color(0xFF000000)),
        val disabledItem: ItemColor = ItemColor(contentColor = Color(0x80EFEFEF))
    ) {
        data class ItemColor internal constructor(
            val containerColor: Color = Color.Transparent,
            val contentColor: Color = Color(0xFF000000)
        )
    }
}

internal fun getColorScheme(darkTheme: Boolean): ColorScheme =
    if (darkTheme) {
        getDarkColors(AppColors())
    } else {
        getLightColors(AppColors())
    }

// Define LightColors using the passed AppColors instance

internal fun getLightColors(colors: AppColors) = lightColorScheme(
    primary = colors.blue.default, // Use Blue as Primary
    onPrimary = colors.blue.default, // Use Blue on Blue (for contrast elements on primary)
    primaryContainer = colors.blue.light1, // Lighter Blue for Primary Container
    onPrimaryContainer = colors.blackAndWhite.black, // Black on Light Blue
    secondary = colors.yellow.default, // Use Yellow as Secondary
    onSecondary = colors.blackAndWhite.black, // Black on Yellow
    secondaryContainer = colors.yellow.light2, // Light Yellow for Secondary Container
    onSecondaryContainer = colors.blackAndWhite.black, // Black on Light Yellow
    background = colors.backgroundSurfaceColors.lightBackground, // Light Background (e.g., white)
    onBackground = colors.blackAndWhite.black, // Black on Background
    surface = colors.backgroundSurfaceColors.lightSurface, // Light Surface (e.g., white)
    onSurface = colors.blackAndWhite.black, // Black on Surface (important for visibility)
    error = colors.red.default, // Red for Error
    onError = colors.blackAndWhite.white, // White on Red (for legibility)
    inversePrimary = colors.blue.light2, // Lighter Blue for Inverse Primary
    inverseSurface = colors.backgroundSurfaceColors.darkSurface, // Dark Surface for Inverse
    inverseOnSurface = colors.blackAndWhite.white, // White on Dark Surface in Inverse
    outline = colors.blackAndWhite.black, // Black for Outlines in Light Mode
    outlineVariant = colors.blackAndWhite.black, // Black for Variant Outlines
    scrim = colors.blackAndWhite.black // Black scrim for light mode
)

// Define DarkColors using the passed AppColors instance

internal fun getDarkColors(colors: AppColors) = darkColorScheme(
    primary = colors.blue.default, // Dark Blue as Primary
    onPrimary = colors.blackAndWhite.white, // White on Dark Blue
    primaryContainer = colors.blue.light2, // Lighter Blue for Primary Container
    onPrimaryContainer = colors.blackAndWhite.white, // White on Lighter Blue
    secondary = colors.yellow.default, // Yellow as Secondary
    onSecondary = colors.blackAndWhite.black, // Black on Yellow
    secondaryContainer = colors.yellow.light2, // Lighter Yellow for Secondary Container
    onSecondaryContainer = colors.blackAndWhite.black, // Black on Light Yellow
    background = colors.backgroundSurfaceColors.darkBackground, // Dark Background (e.g., dark gray)
    onBackground = colors.blackAndWhite.white, // White on Dark Background
    surface = colors.backgroundSurfaceColors.darkSurface, // Dark Surface (e.g., dark gray)
    onSurface = colors.blackAndWhite.white, // White on Dark Surface
    error = colors.red.default, // Red for Error
    onError = colors.blackAndWhite.black, // Black on Red Error
    inversePrimary = colors.blue.light1, // Light Blue for Inverse Primary
    inverseSurface = colors.backgroundSurfaceColors.lightSurface, // Light Surface for Inverse
    inverseOnSurface = colors.blackAndWhite.black, // Black on Light Surface in Inverse
    outline = colors.blackAndWhite.white, // White for Outlines in Dark Mode
    outlineVariant = colors.blackAndWhite.white, // White for Variant Outlines
    scrim = colors.blackAndWhite.black // Black scrim for dark mode
)

/**
 * A Material [ColorScheme] implementation which sets all colors to [debugColor] to discourage usage of
 * [androidx.compose.material3.AppTheme.colorScheme] in preference to [AppTheme.colors].
 */
fun debugColors(
    debugColor: Color = Color.Magenta
) = lightColorScheme(
    primary = debugColor,
    onPrimary = debugColor,
    primaryContainer = debugColor,
    onPrimaryContainer = debugColor,
    inversePrimary = debugColor,
    secondary = debugColor,
    onSecondary = debugColor,
    secondaryContainer = debugColor,
    onSecondaryContainer = debugColor,
    tertiary = debugColor,
    onTertiary = debugColor,
    tertiaryContainer = debugColor,
    onTertiaryContainer = debugColor,
    background = debugColor,
    onBackground = debugColor,
    surface = debugColor,
    onSurface = debugColor,
    surfaceVariant = debugColor,
    onSurfaceVariant = debugColor,
    surfaceTint = debugColor,
    inverseSurface = debugColor,
    inverseOnSurface = debugColor,
    error = debugColor,
    onError = debugColor,
    errorContainer = debugColor,
    onErrorContainer = debugColor,
    outline = debugColor,
    outlineVariant = debugColor,
    scrim = debugColor
)

@Composable
fun defaultTextFieldColors(
    focusedTextColor: Color = AppTheme.colorScheme.onSurface, // Text color when focused
    unfocusedTextColor: Color = AppTheme.colorScheme.onSurfaceVariant, // Text color when unfocused
    disabledTextColor: Color = AppTheme.colorScheme.onSurface.copy(alpha = 0.38f), // Disabled text color
    errorTextColor: Color = AppTheme.colorScheme.error, // Error text color

    focusedContainerColor: Color = AppTheme.colorScheme.surface, // Background color when focused
    unfocusedContainerColor: Color = AppTheme.colorScheme.surfaceVariant, // Background color when unfocused
    disabledContainerColor: Color = AppTheme.colorScheme.surface.copy(alpha = 0.12f), // Disabled background
    errorContainerColor: Color = AppTheme.colorScheme.errorContainer, // Background in error state

    cursorColor: Color = AppTheme.colorScheme.primary, // Cursor color
    errorCursorColor: Color = AppTheme.colorScheme.error, // Cursor color in error state

    selectionColors: TextSelectionColors = LocalTextSelectionColors.current, // Text selection colors

    focusedIndicatorColor: Color = AppTheme.colorScheme.primary, // Underline/Border color when focused
    unfocusedIndicatorColor: Color = AppTheme.colorScheme.outline, // Underline/Border color when unfocused
    disabledIndicatorColor: Color = AppTheme.colorScheme.onSurface.copy(alpha = 0.38f), // Disabled underline
    errorIndicatorColor: Color = AppTheme.colorScheme.error, // Underline/Border color in error state

    focusedLeadingIconColor: Color = AppTheme.colorScheme.onSurface, // Leading icon color when focused
    unfocusedLeadingIconColor: Color = AppTheme.colorScheme.onSurfaceVariant, // Leading icon color when unfocused
    disabledLeadingIconColor: Color = AppTheme.colorScheme.onSurface.copy(alpha = 0.38f), // Disabled leading icon
    errorLeadingIconColor: Color = AppTheme.colorScheme.error, // Leading icon color in error state

    focusedTrailingIconColor: Color = AppTheme.colorScheme.onSurface, // Trailing icon color when focused
    unfocusedTrailingIconColor: Color = AppTheme.colorScheme.onSurfaceVariant, // Trailing icon color when unfocused
    disabledTrailingIconColor: Color = AppTheme.colorScheme.onSurface.copy(alpha = 0.38f), // Disabled trailing icon
    errorTrailingIconColor: Color = AppTheme.colorScheme.error, // Trailing icon color in error state

    focusedLabelColor: Color = AppTheme.colorScheme.onSurface, // Label color when focused
    unfocusedLabelColor: Color = AppTheme.colorScheme.onSurfaceVariant, // Label color when unfocused
    disabledLabelColor: Color = AppTheme.colorScheme.onSurface.copy(alpha = 0.38f), // Disabled label color
    errorLabelColor: Color = AppTheme.colorScheme.error, // Label color in error state

    focusedPlaceholderColor: Color = AppTheme.colorScheme.onSurfaceVariant, // Placeholder color when focused
    unfocusedPlaceholderColor: Color = AppTheme.colorScheme.onSurfaceVariant, // Placeholder color when unfocused
    disabledPlaceholderColor: Color = AppTheme.colorScheme.onSurface.copy(alpha = 0.38f), // Disabled placeholder
    errorPlaceholderColor: Color = AppTheme.colorScheme.error, // Placeholder color in error state

    focusedSupportingTextColor: Color = AppTheme.colorScheme.onSurfaceVariant, // Supporting text when focused
    unfocusedSupportingTextColor: Color = AppTheme.colorScheme.onSurfaceVariant, // Supporting text when unfocused
    disabledSupportingTextColor: Color = AppTheme.colorScheme.onSurface.copy(alpha = 0.38f), // Disabled supporting text
    errorSupportingTextColor: Color = AppTheme.colorScheme.error, // Supporting text in error state

    focusedPrefixColor: Color = AppTheme.colorScheme.onSurface, // Prefix color when focused
    unfocusedPrefixColor: Color = AppTheme.colorScheme.onSurfaceVariant, // Prefix color when unfocused
    disabledPrefixColor: Color = AppTheme.colorScheme.onSurface.copy(alpha = 0.38f), // Disabled prefix
    errorPrefixColor: Color = AppTheme.colorScheme.error, // Prefix color in error state

    focusedSuffixColor: Color = AppTheme.colorScheme.onSurface, // Suffix color when focused
    unfocusedSuffixColor: Color = AppTheme.colorScheme.onSurfaceVariant, // Suffix color when unfocused
    disabledSuffixColor: Color = AppTheme.colorScheme.onSurface.copy(alpha = 0.38f), // Disabled suffix
    errorSuffixColor: Color = AppTheme.colorScheme.error // Suffix color in error state
): TextFieldColors = TextFieldDefaults.colors().copy(
    focusedTextColor = focusedTextColor,
    unfocusedTextColor = unfocusedTextColor,
    disabledTextColor = disabledTextColor,
    errorTextColor = errorTextColor,
    focusedContainerColor = focusedContainerColor,
    unfocusedContainerColor = unfocusedContainerColor,
    disabledContainerColor = disabledContainerColor,
    errorContainerColor = errorContainerColor,
    cursorColor = cursorColor,
    errorCursorColor = errorCursorColor,
    textSelectionColors = selectionColors,
    focusedIndicatorColor = focusedIndicatorColor,
    unfocusedIndicatorColor = unfocusedIndicatorColor,
    disabledIndicatorColor = disabledIndicatorColor,
    errorIndicatorColor = errorIndicatorColor,
    focusedLeadingIconColor = focusedLeadingIconColor,
    unfocusedLeadingIconColor = unfocusedLeadingIconColor,
    disabledLeadingIconColor = disabledLeadingIconColor,
    errorLeadingIconColor = errorLeadingIconColor,
    focusedTrailingIconColor = focusedTrailingIconColor,
    unfocusedTrailingIconColor = unfocusedTrailingIconColor,
    disabledTrailingIconColor = disabledTrailingIconColor,
    errorTrailingIconColor = errorTrailingIconColor,
    focusedLabelColor = focusedLabelColor,
    unfocusedLabelColor = unfocusedLabelColor,
    disabledLabelColor = disabledLabelColor,
    errorLabelColor = errorLabelColor,
    focusedPlaceholderColor = focusedPlaceholderColor,
    unfocusedPlaceholderColor = unfocusedPlaceholderColor,
    disabledPlaceholderColor = disabledPlaceholderColor,
    errorPlaceholderColor = errorPlaceholderColor,
    focusedSupportingTextColor = focusedSupportingTextColor,
    unfocusedSupportingTextColor = unfocusedSupportingTextColor,
    disabledSupportingTextColor = disabledSupportingTextColor,
    errorSupportingTextColor = errorSupportingTextColor,
    focusedPrefixColor = focusedPrefixColor,
    unfocusedPrefixColor = unfocusedPrefixColor,
    disabledPrefixColor = disabledPrefixColor,
    errorPrefixColor = errorPrefixColor,
    focusedSuffixColor = focusedSuffixColor,
    unfocusedSuffixColor = unfocusedSuffixColor,
    disabledSuffixColor = disabledSuffixColor,
    errorSuffixColor = errorSuffixColor
)

// Your CompositionLocal for AppColors (adjust this if you already have it elsewhere)
internal val LocalAppColors = staticCompositionLocalOf { AppColors() }
