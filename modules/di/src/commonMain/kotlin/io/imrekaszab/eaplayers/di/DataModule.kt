package io.imrekaszab.eaplayers.di

import io.imrekaszab.eaplayers.data.api.PlayerApi
import io.imrekaszab.eaplayers.data.api.PlayerApiImpl
import io.imrekaszab.eaplayers.data.service.EAPlayerService
import io.imrekaszab.eaplayers.domain.action.EAPlayerAction
import io.imrekaszab.eaplayers.domain.store.EAPlayerStore
import org.koin.core.parameter.parametersOf
import org.koin.dsl.binds
import org.koin.dsl.module

val dataModule = module {
    single {
        EAPlayerService(
            playerApi = get(),
            logger = get { parametersOf(EAPlayerService::class.simpleName) }
        )
    } binds arrayOf(EAPlayerAction::class, EAPlayerStore::class)
}

var apiModule = module {
    single<PlayerApi> {
        PlayerApiImpl(get())
    }
}
