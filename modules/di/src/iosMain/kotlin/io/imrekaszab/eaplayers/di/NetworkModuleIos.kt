package io.imrekaszab.eaplayers.di

import kotlin.experimental.ExperimentalNativeApi

@OptIn(ExperimentalNativeApi::class)
internal actual fun networkModule(baseUrl: String) =
    networkModule(baseUrl = baseUrl, isDebug = Platform.isDebugBinary)
