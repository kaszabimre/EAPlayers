package theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import eaplayers.composeapp.generated.resources.Poppins_Bold
import eaplayers.composeapp.generated.resources.Poppins_Medium
import eaplayers.composeapp.generated.resources.Poppins_Regular
import eaplayers.composeapp.generated.resources.Poppins_SemiBold
import eaplayers.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.Font

@Composable
fun getPoppins() = FontFamily(
    Font(Res.font.Poppins_Regular, FontWeight.Normal),
    Font(Res.font.Poppins_Medium, FontWeight.Medium),
    Font(Res.font.Poppins_SemiBold, FontWeight.SemiBold),
    Font(Res.font.Poppins_Bold, FontWeight.Bold)
)
