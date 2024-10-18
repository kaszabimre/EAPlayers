package io.imrekaszab.eaplayers.di

import io.ktor.client.engine.okhttp.OkHttp
import org.koin.dsl.module

internal actual fun platformModule() = module {
    factory { OkHttp.create() }
}
