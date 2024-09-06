package io.imrekaszab.eaplayers.core.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun rememberLifecycleFlow(): Flow<Lifecycle.State> {
    val lifecycle = LocalLifecycleOwner.current.lifecycle

    val state = remember { MutableStateFlow(Lifecycle.State.INITIALIZED) }

    val observer = remember {
        LifecycleEventObserver { _, event ->
            state.value = event.targetState
        }
    }

    DisposableEffect(lifecycle, observer) {
        lifecycle.addObserver(observer)
        onDispose {
            lifecycle.removeObserver(observer)
        }
    }

    return state
}
