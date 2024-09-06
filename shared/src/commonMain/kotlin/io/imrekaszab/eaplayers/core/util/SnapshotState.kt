package io.imrekaszab.eaplayers.core.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.Lifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

@Composable
fun <T> StateFlow<T>.collectAsStateInLifecycle(
    context: CoroutineContext = EmptyCoroutineContext,
    minActiveState: Lifecycle.State = Lifecycle.State.STARTED
): State<T> {
    val lifecycleAwareFlow = flowWithLifecycle(minActiveState)

    return lifecycleAwareFlow.collectAsState(
        initial = value,
        context = context
    )
}

@Composable
fun <T> Flow<T>.collectAsStateInLifecycle(
    initial: T,
    context: CoroutineContext = EmptyCoroutineContext,
    minActiveState: Lifecycle.State = Lifecycle.State.STARTED
): State<T> {
    val lifecycleAwareFlow = flowWithLifecycle(minActiveState)

    return lifecycleAwareFlow.collectAsState(
        initial = initial,
        context = context
    )
}
