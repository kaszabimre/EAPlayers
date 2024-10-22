package io.imrekaszab.eaplayers.playerdetails.viewmodel

import io.imrekaszab.eaplayers.core.util.testCommandExecute
import io.imrekaszab.eaplayers.core.util.testParamCommandExecute
import io.imrekaszab.eaplayers.domain.action.EAPlayerAction
import io.imrekaszab.eaplayers.domain.model.MockPlayer
import io.imrekaszab.eaplayers.domain.store.EAPlayerStore
import io.imrekaszab.eaplayers.testing.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class PlayerDetailViewModelTest {

    private val eaPlayerAction: EAPlayerAction = mockk()
    private val eaPlayerStore: EAPlayerStore = mockk()
    private lateinit var viewModel: PlayerDetailViewModel

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @BeforeTest
    fun setUp() {
        viewModel = PlayerDetailViewModel(eaPlayerAction, eaPlayerStore)
    }

    @Test
    fun `loadPlayerDetails should update state with player data`() = runTest(mainDispatcherRule.testDispatcher) {
        // Given
        val mockPlayer = MockPlayer.createMockPlayer().copy(teamMates = emptyList())
        coEvery { eaPlayerStore.getPlayer(1) } returns flowOf(mockPlayer)
        coEvery { eaPlayerAction.selectPlayer(any()) } returns Unit

        // When
        viewModel.loadPlayerDetails.testParamCommandExecute(scope = this, param = 1)
        advanceUntilIdle()

        // Then
        assertEquals(false, viewModel.uiState.value.loading)
        assertEquals(mockPlayer, viewModel.uiState.value.player)
        coVerify { eaPlayerAction.selectPlayer(any()) }
    }

    @Test
    fun `loadPlayerDetails should update state with player who has teammates`() =
        runTest(mainDispatcherRule.testDispatcher) {
            // Given
            val mockPlayerWithTeammates = MockPlayer.createMockPlayer()
            coEvery { eaPlayerStore.getPlayer(2) } returns flowOf(mockPlayerWithTeammates)

            // When
            viewModel.loadPlayerDetails.testParamCommandExecute(scope = this, param = 2)

            // Then
            assertEquals(false, viewModel.uiState.value.loading)
            assertEquals(mockPlayerWithTeammates, viewModel.uiState.value.player)
            coVerify(exactly = 0) { eaPlayerAction.selectPlayer(any()) }
        }

    @Test
    fun `toggleMainStatsExpanded should update state`() = runTest(mainDispatcherRule.testDispatcher) {
        // When
        viewModel.toggleMainStatsExpanded.testCommandExecute(scope = this)

        // Then
        assertEquals(true, viewModel.uiState.value.isMainStatsExpanded)

        // When toggled again
        viewModel.toggleMainStatsExpanded.testCommandExecute(scope = this)

        // Then
        assertEquals(false, viewModel.uiState.value.isMainStatsExpanded)
    }

    @Test
    fun `toggleOtherStatsExpanded should update state`() = runTest(mainDispatcherRule.testDispatcher) {
        // When
        viewModel.toggleOtherStatsExpanded.testCommandExecute(scope = this)

        // Then
        assertEquals(true, viewModel.uiState.value.isOtherStatsExpanded)

        // When toggled again
        viewModel.toggleOtherStatsExpanded.testCommandExecute(scope = this)

        // Then
        assertEquals(false, viewModel.uiState.value.isOtherStatsExpanded)
    }
}
