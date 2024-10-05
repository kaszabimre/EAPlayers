package io.imrekaszab.eaplayers.core.command

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class BaseActionCommand(
    private val canExecuteFlow: StateFlow<Boolean> = MutableStateFlow(true)
) : Command {

    protected val events = MutableSharedFlow<Command.Event<*>>(extraBufferCapacity = 1)

    override val executedEvents: Flow<Command.Event<*>>
        get() = events

    override val canExecute: StateFlow<Boolean>
        get() = canExecuteFlow
}
