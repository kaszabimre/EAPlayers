package io.imrekaszab.eaplayers.data

import co.touchlab.kermit.Logger
import io.imrekaszab.eaplayers.data.api.PlayerApi
import io.imrekaszab.eaplayers.data.mapper.toApiModel
import io.imrekaszab.eaplayers.data.mapper.toPlayer
import io.imrekaszab.eaplayers.data.model.PlayersResponse
import io.imrekaszab.eaplayers.data.service.EAPlayerService
import io.imrekaszab.eaplayers.domain.model.MockPlayer
import io.imrekaszab.eaplayers.testing.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class EAPlayerServiceTests {
    private val playerApi: PlayerApi = mockk()
    private val logger: Logger = mockk(relaxed = true)
    private lateinit var service: EAPlayerService

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @BeforeTest
    fun setup() {
        service = EAPlayerService(playerApi, logger)
    }

    @Test
    fun `refreshPlayers should call playerApi and update playersStateFlow`() =
        runTest(mainDispatcherRule.testDispatcher) {
            // Given
            val search = "search"
            val mockPlayerResponse = listOf(MockPlayer.createMockPlayer().toApiModel())
            coEvery { playerApi.getPlayersResponse(any()) } returns PlayersResponse(
                items = mockPlayerResponse,
                totalItems = 1
            )

            // When
            service.refreshPlayers(search)

            // Then
            val players = service.getPlayerList().first()
            assertEquals(mockPlayerResponse.map { it.toPlayer() }, players)
            coVerify { playerApi.getPlayersResponse(search) }
        }

    @Test
    fun `selectPlayer should update player with teammates and emit new state`() =
        runTest(mainDispatcherRule.testDispatcher) {
            // Given
            val mockPlayer = MockPlayer.createMockPlayer()
            val mockTeamMates = listOf(mockPlayer.teamMates[0].toApiModel(), mockPlayer.toApiModel())

            coEvery { playerApi.getPlayersResponseByTeam(mockPlayer.team.id) } returns PlayersResponse(
                items = mockTeamMates,
                totalItems = 2
            )

            coEvery { playerApi.getPlayersResponse(any()) } returns PlayersResponse(
                items = listOf(mockPlayer.toApiModel()),
                totalItems = 2
            )

            // Refresh players first
            service.refreshPlayers("search")

            // When
            service.selectPlayer(mockPlayer)

            // Then
            val players = service.getPlayerList().first()
            assertEquals(2, players.size) // The player + the teammate
            assertEquals(mockTeamMates.map { it.toPlayer() }, players.first { it.id == mockPlayer.id }.teamMates)
            coVerify { logger.d(any()) }
            coVerify { playerApi.getPlayersResponseByTeam(mockPlayer.team.id) }
        }

    @Test
    fun `getPlayer should return the player with the given id`() = runTest {
        // Given
        val mockPlayer = MockPlayer.createMockPlayer()
        val mockPlayerResponse = listOf(mockPlayer.toApiModel())

        // Mock the API response
        coEvery { playerApi.getPlayersResponse(any()) } returns PlayersResponse(
            items = mockPlayerResponse,
            totalItems = 1
        )

        // Refresh players first
        service.refreshPlayers("search")

        // When
        val player = service.getPlayer(mockPlayer.id).first()

        // Then
        assertEquals(mockPlayer.id, player.id)
    }
}
