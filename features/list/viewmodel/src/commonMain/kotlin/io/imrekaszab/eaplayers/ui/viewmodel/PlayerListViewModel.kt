package io.imrekaszab.eaplayers.ui.viewmodel

import androidx.lifecycle.ViewModel
import io.imrekaszab.eaplayers.core.util.command
import io.imrekaszab.eaplayers.domain.action.EAPlayerAction
import io.imrekaszab.eaplayers.domain.store.EAPlayerStore
import io.imrekaszab.eaplayers.ui.model.PlayerListState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first

class PlayerListViewModel(eaPlayerAction: EAPlayerAction, eaPlayerStore: EAPlayerStore) :
    ViewModel() {
    private val _uiState = MutableStateFlow(PlayerListState())
    val uiState: StateFlow<PlayerListState> = _uiState.asStateFlow()

    val refreshPlayers = command { textFieldValue: String ->
        if (textFieldValue.isNotEmpty()) {
            _uiState.emit(uiState.first().copy(loading = true))
            eaPlayerAction.refreshPlayers(search = textFieldValue)
        } else if (textFieldValue.isEmpty()) {
            _uiState.emit(uiState.first().copy(loading = true))
            eaPlayerAction.refreshPlayers("")
        }
        val players = eaPlayerStore.getPlayerList().first()
        _uiState.emit(
            PlayerListState(
                players = players,
                textFieldValue = textFieldValue,
                loading = false
            )
        )
    }
}
