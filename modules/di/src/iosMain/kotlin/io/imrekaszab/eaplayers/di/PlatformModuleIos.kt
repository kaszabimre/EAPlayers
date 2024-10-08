package io.imrekaszab.eaplayers.di

import io.ktor.client.engine.darwin.Darwin
import org.koin.dsl.module

internal actual fun platformModule() = module {
    factory {
        Darwin.create()
    }
}
