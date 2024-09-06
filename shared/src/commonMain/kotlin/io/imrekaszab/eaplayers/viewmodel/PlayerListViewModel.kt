package io.imrekaszab.eaplayers.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.imrekaszab.eaplayers.PlayerListState
import io.imrekaszab.eaplayers.core.util.command
import io.imrekaszab.eaplayers.core.util.invoke
import io.imrekaszab.eaplayers.core.util.launchOnDefault
import io.imrekaszab.eaplayers.domain.action.EAPlayerAction
import io.imrekaszab.eaplayers.domain.model.Player
import io.imrekaszab.eaplayers.domain.store.EAPlayerStore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first

class PlayerListViewModel(eaPlayerAction: EAPlayerAction, eaPlayerStore: EAPlayerStore) :
    ViewModel() {
    private val _uiState = MutableStateFlow(PlayerListState())
    val uiState: StateFlow<PlayerListState> = _uiState.asStateFlow()

    val refreshPlayers = command { textFieldValue: String ->
        if (textFieldValue.length > 2) {
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

    val selectPlayer = command { player: Player ->
        eaPlayerAction.selectPlayer(player)
    }

    init {
        viewModelScope.launchOnDefault {
            refreshPlayers(uiState.value.textFieldValue)
        }
    }
}
