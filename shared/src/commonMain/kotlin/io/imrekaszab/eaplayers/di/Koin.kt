package io.imrekaszab.eaplayers.di

import co.touchlab.kermit.Logger
import co.touchlab.kermit.StaticConfig
import co.touchlab.kermit.platformLogWriter
import io.imrekaszab.eaplayers.network.apiModule
import io.imrekaszab.eaplayers.network.networkModule
import org.koin.core.KoinApplication
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module

fun initKoin(appModule: Module, baseUrl: String): KoinApplication =
    startKoin {
        modules(
            appModule,
            apiModule,
            networkModule(baseUrl = baseUrl),
            factoryModule,
            viewModelModule,
            dataModule
        )
    }

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
