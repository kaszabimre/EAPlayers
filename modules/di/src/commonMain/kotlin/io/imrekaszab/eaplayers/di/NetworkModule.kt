package io.imrekaszab.eaplayers.di

import io.ktor.client.HttpClient
import io.ktor.client.plugins.Charsets
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.URLBuilder
import io.ktor.http.encodedPath
import io.ktor.http.takeFrom
import io.ktor.serialization.kotlinx.json.json
import io.ktor.utils.io.charsets.Charsets
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import org.koin.core.module.Module
import org.koin.dsl.module

internal expect fun networkModule(baseUrl: String): Module

internal fun networkModule(isDebug: Boolean, baseUrl: String) = module {
    factory {
        HttpClient(engine = get()) {
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

            Charsets {
                register(Charsets.UTF_8)
            }

            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                        isLenient = true
                        @OptIn(ExperimentalSerializationApi::class)
                        explicitNulls = false
                    }
                )
            }

            if (isDebug) {
                install(Logging) {
                    level = LogLevel.ALL
                }
            }
        }
    }
}
