package io.imrekaszab.eaplayers.network

import io.imrekaszab.eaplayers.model.PlayersResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class PlayerApiImpl(private val httpClient: HttpClient) : PlayerApi {
    override suspend fun getPlayersResponse(search: String): PlayersResponse? {
        val request = httpClient.get("rating/fc-24") {
            parameter("limit", 100)
            if (search.isNotEmpty()) {
                parameter("search", search)
            }
        }

        return if (request.status == io.ktor.http.HttpStatusCode.OK) {
            request.body()
        } else {
            null
        }
    }

    override suspend fun getPlayersResponseByTeam(teamId: Int): PlayersResponse? {
        val request = httpClient.get("rating/fc-24") {
            parameter("limit", 100)
            parameter("team", teamId)
        }

        return if (request.status == io.ktor.http.HttpStatusCode.OK) {
            request.body()
        } else {
            null
        }
    }
}
