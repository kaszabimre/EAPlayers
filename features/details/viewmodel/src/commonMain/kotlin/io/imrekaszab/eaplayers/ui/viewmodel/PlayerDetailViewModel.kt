package io.imrekaszab.eaplayers.ui.viewmodel

import androidx.lifecycle.ViewModel
import io.imrekaszab.eaplayers.core.util.command
import io.imrekaszab.eaplayers.domain.action.EAPlayerAction
import io.imrekaszab.eaplayers.domain.store.EAPlayerStore
import io.imrekaszab.eaplayers.ui.model.PlayerDetailState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull

class PlayerDetailViewModel(
    private val eaPlayerAction: EAPlayerAction,
    private val eaPlayerStore: EAPlayerStore
) : ViewModel() {

    private val _uiState = MutableStateFlow(PlayerDetailState())
    val uiState: StateFlow<PlayerDetailState> = _uiState.asStateFlow()

    val loadPlayerDetails = command { playerId: Int ->
        _uiState.emit(PlayerDetailState(loading = true))
        delay(200L)
        val playerList =
            eaPlayerStore.getPlayerList().firstOrNull() ?: emptyList()
        val player = playerList.firstOrNull { it.id == playerId } ?: return@command

        if (player.teamMates.isEmpty()) {
            eaPlayerAction.selectPlayer(player)
            _uiState.emit(
                PlayerDetailState(
                    player = eaPlayerStore.getPlayerList().firstOrNull()
                        ?.first { it.id == playerId },
                    loading = false
                )
            )
        } else {
            _uiState.emit(PlayerDetailState(player = player, loading = false))
        }
    }
}
