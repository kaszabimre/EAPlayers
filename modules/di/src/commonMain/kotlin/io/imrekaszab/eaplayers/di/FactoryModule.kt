package io.imrekaszab.eaplayers.di

import co.touchlab.kermit.Logger
import co.touchlab.kermit.StaticConfig
import co.touchlab.kermit.platformLogWriter
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module

internal val factoryModule = module {
    val baseLogger =
        Logger(
            config = StaticConfig(logWriterList = listOf(platformLogWriter())),
            "EAPlayers"
        )
    factory { (tag: String?) -> if (tag != null) baseLogger.withTag(tag) else baseLogger }
}

// Simple function to clean up the syntax a bit
fun KoinComponent.injectLogger(tag: String): Lazy<Logger> = inject { parametersOf(tag) }
