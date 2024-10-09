package io.imrekaszab.eaplayers.di

import co.touchlab.kermit.Logger
import io.imrekaszab.eaplayers.logger.logger
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module

internal val factoryModule = module {
    factory { (tag: String?) -> logger(tag = tag) }
}

// Simple function to clean up the syntax a bit
fun KoinComponent.injectLogger(tag: String): Lazy<Logger> = inject { parametersOf(tag) }
