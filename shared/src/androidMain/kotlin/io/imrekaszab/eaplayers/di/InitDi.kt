package io.imrekaszab.eaplayers.di

import android.content.Context
import org.koin.dsl.module

fun initDi(context: Context, baseUrl: String) {
    initKoin(
        appModule = module {
            single<Context> { context }
        },
        baseUrl
    )
}
