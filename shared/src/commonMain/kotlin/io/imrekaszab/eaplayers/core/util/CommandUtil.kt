package io.imrekaszab.eaplayers.core.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.imrekaszab.eaplayers.core.command.ActionCommand
import io.imrekaszab.eaplayers.core.command.Command
import io.imrekaszab.eaplayers.core.command.ParameterCommand
import io.imrekaszab.eaplayers.core.command.execute
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.plus

fun command(
    scope: CoroutineScope = CoroutineScope(Dispatchers.Default),
    canExecute: StateFlow<Boolean> = MutableStateFlow(true),
    action: suspend () -> Unit
): Command = ActionCommand(scope, action, canExecute)

fun <T> command(
    scope: CoroutineScope = CoroutineScope(Dispatchers.Default),
    canExecute: StateFlow<Boolean> = MutableStateFlow(true),
    action: suspend (T) -> Unit
): ParameterCommand<T> =
    ParameterCommand(scope, action, canExecute)

fun ViewModel.command(
    canExecute: StateFlow<Boolean> = MutableStateFlow(true),
    action: suspend () -> Unit
): Command = command(viewModelScope + Dispatchers.Default, canExecute, action)

fun <T> ViewModel.command(
    canExecute: StateFlow<Boolean> = MutableStateFlow(true),
    action: suspend (T) -> Unit
): ParameterCommand<T> = command(viewModelScope + Dispatchers.Default, canExecute, action)

fun Command.testCommandExecute(scope: CoroutineScope) {
    val actionCommand = this as? ActionCommand
    actionCommand ?: return
    val testCommand = ActionCommand(action = actionCommand.action, scope = scope)
    testCommand.execute()
}

fun <T> ParameterCommand<T>.testParamCommandExecute(scope: CoroutineScope, param: T) {
    val actionCommand = this as? ParameterCommand<T>
    actionCommand ?: return
    val testCommand =
        command(scope = scope, action = actionCommand.action, canExecute = actionCommand.canExecute)
    testCommand.execute(param)
}

operator fun Command.invoke() = execute()

operator fun <T> ParameterCommand<T>.invoke(param: T) = execute(param)
