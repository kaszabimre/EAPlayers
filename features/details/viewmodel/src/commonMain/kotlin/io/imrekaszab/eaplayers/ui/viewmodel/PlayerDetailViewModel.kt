package io.imrekaszab.eaplayers.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.imrekaszab.eaplayers.core.command.execute
import io.imrekaszab.eaplayers.core.util.command
import io.imrekaszab.eaplayers.domain.action.EAPlayerAction
import io.imrekaszab.eaplayers.domain.model.Player
import io.imrekaszab.eaplayers.domain.store.EAPlayerStore
import io.imrekaszab.eaplayers.ui.model.PlayerDetailState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class PlayerDetailViewModel(
    private val eaPlayerAction: EAPlayerAction,
    private val eaPlayerStore: EAPlayerStore
) : ViewModel() {

    private val _uiState = MutableStateFlow(PlayerDetailState())
    val uiState: StateFlow<PlayerDetailState> = _uiState.asStateFlow()

    val selectPlayer = command { player: Player ->
        eaPlayerAction.selectPlayer(player)
    }

    val loadPlayerDetails = command { playerId: Int ->
        _uiState.value = PlayerDetailState(loading = true)
        val playerList = eaPlayerStore.getPlayerList().firstOrNull() ?: emptyList()
        val player = playerList.firstOrNull { it.id == playerId }

        _uiState.value = PlayerDetailState(
            player = player,
            loading = false
        )
    }

    init {
        viewModelScope.launch {
            eaPlayerStore.getPlayerList().collectLatest { _ ->
                loadPlayerDetails.execute(uiState.value.player?.id ?: -1)
            }
        }
    }
}
