package theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Suppress("MagicNumber")
@Immutable
data class AppColors(
    val primary: Color = Blue().default,              // Use Blue as Primary
    val onPrimary: Color = BlackAndWhite().white,     // White on Blue
    val primaryContainer: Color = Blue().light1,      // Lighter Blue
    val onPrimaryContainer: Color = BlackAndWhite().black, // Black on Light Blue
    val secondary: Color = Yellow().default,          // Use Yellow as Secondary
    val onSecondary: Color = BlackAndWhite().black,   // Black on Yellow
    val secondaryContainer: Color = Yellow().light2,  // Light Yellow
    val onSecondaryContainer: Color = BlackAndWhite().black, // Black on Light Yellow
    val backgroundSurfaceColors: BackgroundSurfaceColors = BackgroundSurfaceColors(), // New Background and Surface Colors
    val blackAndWhite: BlackAndWhite = BlackAndWhite(),
    val red: Red = Red(),
    val yellow: Yellow = Yellow(),  // Yellow Color
    val blue: Blue = Blue(),        // Blue Color
) {
    // Define Yellow color variations
    data class Yellow internal constructor(
        val default: Color = Color(0xFFFFC107), // Default Yellow (Secondary)
        val light1: Color = Color(0xFFFFD54F),  // Lighter shade of Yellow
        val light2: Color = Color(0xFFFFECB3),  // Lighter yellow variant
        val light3: Color = Color(0xFFFFF9C4)   // Lightest shade of Yellow
    )

    // Define Blue color variations
    data class Blue internal constructor(
        val default: Color = Color(0xFF0D47A1), // Default Dark Blue (Primary)
        val light1: Color = Color(0xFF5472D3),  // Lighter Blue (PrimaryContainer)
        val light2: Color = Color(0xFFBBDEFB),  // Light Blue variant
        val light3: Color = Color(0xFFE3F2FD)   // Lightest Blue
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
        val lightBackground: Color = Color(0xFFF6F6F6),  // Light Background
        val lightSurface: Color = Color.White,           // Light Surface
        val darkBackground: Color = Color(0xFF121212),   // Dark Background
        val darkSurface: Color = Color(0xFF1D1D1D)       // Dark Surface
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
    primary = colors.blue.default,               // Use Blue as Primary
    onPrimary = colors.blackAndWhite.white,      // White on Blue
    primaryContainer = colors.blue.light1,       // Lighter Blue
    onPrimaryContainer = colors.blackAndWhite.black,  // Black on Light Blue
    secondary = colors.yellow.default,           // Use Yellow as Secondary
    onSecondary = colors.blackAndWhite.black,    // Black on Yellow
    secondaryContainer = colors.yellow.light2,   // Light Yellow
    onSecondaryContainer = colors.blackAndWhite.black, // Black on Light Yellow
    background = colors.backgroundSurfaceColors.lightBackground, // Light Background
    onBackground = colors.blackAndWhite.black,   // Black on Background
    surface = colors.backgroundSurfaceColors.lightSurface,       // Light Surface
    onSurface = colors.blackAndWhite.black,      // Black on Surface
    error = colors.red.default,                  // Red as Error
    onError = colors.blackAndWhite.white         // White on Red
)

// Define DarkColors using the passed AppColors instance

internal fun getDarkColors(colors: AppColors) = darkColorScheme(
    primary = colors.blue.default,               // Use Blue as Primary
    onPrimary = colors.blackAndWhite.white,      // White on Blue
    primaryContainer = colors.blue.light1,       // Use Lighter Blue
    onPrimaryContainer = colors.blackAndWhite.white, // White on Darker Blue
    secondary = colors.yellow.default,           // Use Yellow as Secondary
    onSecondary = colors.blackAndWhite.black,    // Black on Yellow
    secondaryContainer = colors.yellow.light2,   // Darker Yellow
    onSecondaryContainer = colors.blackAndWhite.black, // Black on Dark Yellow
    background = colors.backgroundSurfaceColors.darkBackground, // Dark Background
    onBackground = colors.blackAndWhite.white,   // White on Background
    surface = colors.backgroundSurfaceColors.darkSurface,        // Dark Surface color
    onSurface = colors.blackAndWhite.white,      // White on Surface
    error = colors.red.default,                  // Red as Error
    onError = colors.blackAndWhite.black         // Black on Red
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


// Your CompositionLocal for AppColors (adjust this if you already have it elsewhere)
internal val LocalAppColors = staticCompositionLocalOf { AppColors() }
