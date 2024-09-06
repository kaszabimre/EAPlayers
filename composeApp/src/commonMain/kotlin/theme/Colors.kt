package theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

internal val LightColors = lightColorScheme(
    primary = Color(0xFF0D47A1), // Dark Blue
    onPrimary = Color.White,
    primaryContainer = Color(0xFF5472D3), // Lighter Blue
    onPrimaryContainer = Color.Black,
    secondary = Color(0xFFFFC107), // Yellow
    onSecondary = Color.Black,
    secondaryContainer = Color(0xFFFFE082), // Light Yellow
    onSecondaryContainer = Color.Black,
    background = Color(0xFFF6F6F6),
    onBackground = Color.Black,
    surface = Color.White,
    onSurface = Color.Black,
    error = Color(0xFFB00020),
    onError = Color.White,
)

internal val DarkColors = darkColorScheme(
    primary = Color(0xFF0D47A1), // Dark Blue
    onPrimary = Color.White,
    primaryContainer = Color(0xFF001970), // Even Darker Blue
    onPrimaryContainer = Color.White,
    secondary = Color(0xFFFFC107), // Yellow
    onSecondary = Color.Black,
    secondaryContainer = Color(0xFFFFB300), // Darker Yellow
    onSecondaryContainer = Color.Black,
    background = Color(0xFF121212),
    onBackground = Color.White,
    surface = Color(0xFF1D1D1D),
    onSurface = Color.White,
    error = Color(0xFFCF6679),
    onError = Color.Black,
)
