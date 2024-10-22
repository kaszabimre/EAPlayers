package io.imrekaszab.eaplayers.playerdetails.viewmodel

import androidx.lifecycle.ViewModel
import io.imrekaszab.eaplayers.core.util.command
import io.imrekaszab.eaplayers.domain.action.EAPlayerAction
import io.imrekaszab.eaplayers.domain.store.EAPlayerStore
import io.imrekaszab.eaplayers.playerdetails.model.PlayerDetailState
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
        val player = eaPlayerStore.getPlayer(playerId).firstOrNull() ?: return@command

        if (player.teamMates.isEmpty()) {
            eaPlayerAction.selectPlayer(player)
            _uiState.emit(
                PlayerDetailState(
                    player = eaPlayerStore.getPlayer(playerId).firstOrNull(),
                    loading = false
                )
            )
        } else {
            _uiState.emit(PlayerDetailState(player = player, loading = false))
        }
    }

    val toggleMainStatsExpanded = command {
        _uiState.emit(uiState.value.copy(isMainStatsExpanded = !uiState.value.isMainStatsExpanded))
    }

    val toggleOtherStatsExpanded = command {
        _uiState.emit(uiState.value.copy(isOtherStatsExpanded = !uiState.value.isOtherStatsExpanded))
    }
}
