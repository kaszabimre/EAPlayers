package io.imrekaszab.eaplayers.di

import io.imrekaszab.eaplayers.data.service.EAPlayerService
import io.imrekaszab.eaplayers.domain.action.EAPlayerAction
import io.imrekaszab.eaplayers.domain.store.EAPlayerStore
import org.koin.dsl.binds
import org.koin.dsl.module

val dataModule = module {
    single { EAPlayerService(get()) } binds arrayOf(EAPlayerAction::class, EAPlayerStore::class)
}
