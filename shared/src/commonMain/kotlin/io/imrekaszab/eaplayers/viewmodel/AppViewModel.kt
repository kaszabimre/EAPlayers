package io.imrekaszab.eaplayers.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.imrekaszab.eaplayers.AppState
import io.imrekaszab.eaplayers.core.util.command
import io.imrekaszab.eaplayers.core.util.launchOnDefault
import io.imrekaszab.eaplayers.domain.action.EAPlayerAction
import io.imrekaszab.eaplayers.domain.store.EAPlayerStore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest

class AppViewModel(eaPlayerAction: EAPlayerAction, eaPlayerStore: EAPlayerStore) :
    ViewModel() {
    private val _uiState = MutableStateFlow(AppState())
    val uiState: StateFlow<AppState> = _uiState.asStateFlow()

    val switchDarkMode = command {
        eaPlayerAction.toggleDarkMode()
    }

    init {
        viewModelScope.launchOnDefault {
            eaPlayerStore.isDarkModeActive().collectLatest {
                _uiState.emit(uiState.value.copy(darkMode = it))
            }
        }
    }
}
