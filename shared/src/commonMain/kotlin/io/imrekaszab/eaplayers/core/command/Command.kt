package io.imrekaszab.eaplayers.core.command

import androidx.compose.runtime.Immutable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

@Immutable
interface Command {

    val executedEvents: Flow<Event<*>>

    val canExecute: StateFlow<Boolean>

    fun execute()

    data class Event<T>(val executed: Boolean, val param: T? = null)
}
