package io.imrekaszab.eaplayers.di

import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.Module

internal fun initKoin(
    appModule: Module,
    baseUrl: String,
): KoinApplication = startKoin {
    modules(
        appModule,
        apiModule,
        networkModule(baseUrl = baseUrl),
        factoryModule,
        viewModelModule,
        dataModule,
        platformModule()
    )
}
