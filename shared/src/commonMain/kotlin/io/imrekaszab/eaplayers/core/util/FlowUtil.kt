package io.imrekaszab.eaplayers.core.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.flowWithLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map

fun <T> Flow<T>.launchInLifecycle(
    lifecycle: Lifecycle,
    minActiveState: Lifecycle.State = Lifecycle.State.STARTED
) = flowWithLifecycle(lifecycle, minActiveState)
    .launchIn(lifecycle.coroutineScope)

@Composable
fun <T> Flow<T>.flowWithLifecycle(minActiveState: Lifecycle.State = Lifecycle.State.STARTED): Flow<T> {
    val lifecycleOwner = LocalLifecycleOwner.current

    return remember(this, lifecycleOwner, minActiveState) {
        flowWithLifecycle(lifecycleOwner.lifecycle, minActiveState)
    }
}

fun <T> flowOf(factory: () -> T): Flow<T> = flow {
    emit(factory())
}

inline fun <T, R> Flow<List<T>>.mapList(crossinline transform: (T) -> R): Flow<List<R>> = this.map { list ->
    list.map(transform)
}
