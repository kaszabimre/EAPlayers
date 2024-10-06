package io.imrekaszab.eaplayers.core.command

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.launch

open class ParameterCommand<T>(
    private val scope: CoroutineScope = CoroutineScope(Dispatchers.Default),
    val action: suspend (T) -> Unit,
    canExecuteFlow: StateFlow<Boolean>
) : BaseActionCommand(canExecuteFlow) {

    var parameterProvider: (() -> T)? = null

    override val executedEvents: Flow<Command.Event<T>>
        get() = events.filterIsInstance()

    override fun execute() {
        val invokeAction = canExecute.value && parameterProvider != null
        val param = parameterProvider?.invoke()

        if (invokeAction && param != null) {
            scope.launch {
                action(param)
            }
        }

        events.tryEmit(Command.Event(executed = invokeAction, param = param))
    }
}

fun <T> ParameterCommand<T>.withParam(param: T): ParameterCommand<T> =
    also { parameterProvider = { param } }

fun <T> ParameterCommand<T>.execute(param: T) = withParam(param).execute()
