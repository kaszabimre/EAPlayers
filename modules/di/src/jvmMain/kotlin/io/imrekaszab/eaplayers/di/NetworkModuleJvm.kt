package io.imrekaszab.eaplayers.di

internal actual fun networkModule(baseUrl: String) = networkModule(baseUrl = baseUrl, isDebug = true)
