package io.imrekaszab.eaplayers.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import eaplayers.modules.theme.generated.resources.Poppins_Black
import eaplayers.modules.theme.generated.resources.Poppins_Bold
import eaplayers.modules.theme.generated.resources.Poppins_Light
import eaplayers.modules.theme.generated.resources.Poppins_Medium
import eaplayers.modules.theme.generated.resources.Poppins_Regular
import eaplayers.modules.theme.generated.resources.Poppins_SemiBold
import eaplayers.modules.theme.generated.resources.Res
import org.jetbrains.compose.resources.Font

@Composable
fun getDefaultFontFamily(): FontFamily =
    FontFamily(
        Font(Res.font.Poppins_Regular, FontWeight.Normal),
        Font(Res.font.Poppins_Medium, FontWeight.Medium),
        Font(Res.font.Poppins_Light, FontWeight.Light),
        Font(Res.font.Poppins_SemiBold, FontWeight.SemiBold),
        Font(Res.font.Poppins_Bold, FontWeight.Bold),
        Font(Res.font.Poppins_Black, FontWeight.Black)
    )

@Immutable
data class AppTypography(
    val dimens: AppDimens,
    val scheme: ColorScheme,
    val fontFamily: FontFamily,
    val heading: Heading = Heading(dimens, scheme, fontFamily),
    val body: Body = Body(dimens, scheme, fontFamily),
    val button: Button = Button(dimens, fontFamily)
)

data class Heading internal constructor(
    private val dimens: AppDimens,
    val scheme: ColorScheme,
    private val fontFamily: FontFamily,
    val large: TextStyle = TextStyle(
        fontFamily = fontFamily,
        fontSize = dimens.fontSize.headerL,
        fontWeight = FontWeight.Bold,
        lineHeight = dimens.lineHeight.headerL,
        color = scheme.onPrimary
    ),
    val medium: TextStyle = large.copy(
        fontSize = dimens.fontSize.headerM,
        lineHeight = dimens.lineHeight.headerM
    ),
    val small: TextStyle = large.copy(
        fontSize = dimens.fontSize.headerS,
        lineHeight = dimens.lineHeight.headerS
    ),
    val extraSmall: TextStyle = TextStyle(
        fontFamily = fontFamily,
        fontSize = dimens.fontSize.headerXS,
        fontWeight = FontWeight.W500,
        color = scheme.onPrimary
    )
)

data class Body internal constructor(
    private val dimens: AppDimens,
    val scheme: ColorScheme,
    private val fontFamily: FontFamily,
    val large: TextStyle = TextStyle(
        fontFamily = fontFamily,
        fontSize = dimens.fontSize.bodyL,
        lineHeight = dimens.lineHeight.bodyL,
        fontWeight = FontWeight.W500,
        color = scheme.onPrimary
    ),
    val medium: TextStyle = TextStyle(
        fontFamily = fontFamily,
        fontSize = dimens.fontSize.bodyM,
        fontWeight = FontWeight.W500,
        color = scheme.onPrimary
    ),
    val small: TextStyle = medium.copy(
        fontSize = dimens.fontSize.bodyS,
        fontWeight = FontWeight.Normal
    ),
    val extraSmall: TextStyle = medium.copy(
        fontSize = dimens.fontSize.bodyXS,
    ),
)

data class Button internal constructor(
    private val dimens: AppDimens,
    private val fontFamily: FontFamily,
    val large: TextStyle = TextStyle(
        fontFamily = fontFamily,
        fontSize = dimens.fontSize.buttonL,
        fontWeight = FontWeight.W500,
        lineHeight = dimens.lineHeight.buttonL,
        letterSpacing = (-0.01).em,
    ),
    val medium: TextStyle = large.copy(
        fontSize = dimens.fontSize.buttonM,
        lineHeight = dimens.lineHeight.buttonM,
    ),
    val small: TextStyle = large.copy(
        fontSize = dimens.fontSize.buttonS,
        lineHeight = dimens.lineHeight.buttonS,
    ),
    val tiny: TextStyle = large.copy(
        fontSize = dimens.fontSize.buttonT,
        lineHeight = dimens.lineHeight.buttonT,
    )
)

@Composable
fun ProvideAppTypography(
    dimens: AppDimens,
    scheme: ColorScheme,
    content: @Composable () -> Unit
) {
    val typography = AppTypography(
        dimens = dimens,
        scheme = scheme,
        fontFamily = getDefaultFontFamily()
    )

    CompositionLocalProvider(LocalAppTypography provides typography, content = content)
}

internal val LocalAppTypography =
    staticCompositionLocalOf<AppTypography> { error("No AppTypography provided") }
