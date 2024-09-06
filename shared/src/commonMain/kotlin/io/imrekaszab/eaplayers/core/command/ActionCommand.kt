package io.imrekaszab.eaplayers.core.command

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

open class ActionCommand(
    private val scope: CoroutineScope,
    val action: suspend () -> Unit,
    canExecuteFlow: StateFlow<Boolean> = MutableStateFlow(true)
) : BaseActionCommand(canExecuteFlow) {

    override fun execute() {
        val shouldInvokeAction = canExecute.value

        if (shouldInvokeAction) {
            scope.launch {
                action()
            }
        }

        events.tryEmit(Command.Event<Unit>(executed = shouldInvokeAction))
    }
}
