package io.imrekaszab.eaplayers.di

import org.koin.dsl.module

fun initKoinJvm(baseUrl: String) {
    initKoin(appModule = module {}, baseUrl = baseUrl)
}
