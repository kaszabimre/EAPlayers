package io.imrekaszab.eaplayers.network

import io.imrekaszab.eaplayers.data.api.PlayerApi
import io.imrekaszab.eaplayers.data.api.PlayerApiImpl
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.URLBuilder
import io.ktor.http.encodedPath
import io.ktor.http.takeFrom
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

var apiModule = module {
    single<PlayerApi> {
        PlayerApiImpl(get())
    }
}

fun networkModule(baseUrl: String) = module {
    single {
        HttpClient {
            defaultRequest {
                url.takeFrom(
                    URLBuilder().takeFrom(baseUrl).apply {
                        encodedPath += "/${url.encodedPath}"
                    }
                )
            }

            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                    }
                )
            }

            install(Logging) {
                level = LogLevel.INFO
            }
        }
    }
}
