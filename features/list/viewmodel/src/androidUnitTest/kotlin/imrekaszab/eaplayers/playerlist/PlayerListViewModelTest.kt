package imrekaszab.eaplayers.playerlist

import io.imrekaszab.eaplayers.core.util.testParamCommandExecute
import io.imrekaszab.eaplayers.domain.action.EAPlayerAction
import io.imrekaszab.eaplayers.domain.model.MockPlayer
import io.imrekaszab.eaplayers.domain.store.EAPlayerStore
import io.imrekaszab.eaplayers.playerlist.viewmodel.PlayerListViewModel
import io.imrekaszab.eaplayers.testing.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class PlayerListViewModelTest {

    private val eaPlayerAction: EAPlayerAction = mockk()
    private val eaPlayerStore: EAPlayerStore = mockk()
    private lateinit var viewModel: PlayerListViewModel

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @BeforeTest
    fun setUp() {
        viewModel = PlayerListViewModel(eaPlayerAction, eaPlayerStore)
    }

    @Test
    fun `refreshPlayers with non-empty textFieldValue should call action and update state`() =
        runTest(mainDispatcherRule.testDispatcher) {
            // Given
            val searchValue = "some value"
            val playerList = listOf(MockPlayer.createMockPlayer())
            coEvery { eaPlayerStore.getPlayerList() } returns flowOf(playerList)
            coEvery { eaPlayerAction.refreshPlayers(any()) } returns Unit

            // When
            viewModel.refreshPlayers.testParamCommandExecute(scope = this, param = searchValue)

            // Then
            assertEquals(false, viewModel.uiState.value.loading)
            assertEquals(playerList, viewModel.uiState.value.players)
            assertEquals(searchValue, viewModel.uiState.value.textFieldValue)
            coVerify { eaPlayerAction.refreshPlayers(searchValue) }
        }

    @Test
    fun `refreshPlayers with empty textFieldValue should call action with empty string`() =
        runTest(mainDispatcherRule.testDispatcher) {
            // Given
            val playerList = listOf(MockPlayer.createMockPlayer())
            coEvery { eaPlayerStore.getPlayerList() } returns flowOf(playerList)
            coEvery { eaPlayerAction.refreshPlayers(any()) } returns Unit

            // When
            viewModel.refreshPlayers.testParamCommandExecute(scope = this, param = "")

            // Then
            assertEquals(false, viewModel.uiState.value.loading)
            assertEquals(playerList, viewModel.uiState.value.players)
            assertEquals("", viewModel.uiState.value.textFieldValue)
            coVerify { eaPlayerAction.refreshPlayers("") }
        }
}
