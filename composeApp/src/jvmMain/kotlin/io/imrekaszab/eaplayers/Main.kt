package io.imrekaszab.eaplayers

import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import eaplayers.composeapp.generated.resources.Res
import eaplayers.composeapp.generated.resources.app_name
import eaplayers.composeapp.generated.resources.base_url
import io.imrekaszab.eaplayers.di.initKoinJvm
import io.imrekaszab.eaplayers.navigation.Navigation
import org.jetbrains.compose.resources.stringResource

fun main() = application {
    initKoinJvm(stringResource(Res.string.base_url))
    val windowState = WindowState(size = DpSize(400.dp, 700.dp))

    Window(
        onCloseRequest = ::exitApplication,
        state = windowState,
        title = stringResource(Res.string.app_name)
    ) { Navigation() }
}
