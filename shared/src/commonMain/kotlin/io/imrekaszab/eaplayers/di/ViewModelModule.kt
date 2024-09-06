package io.imrekaszab.eaplayers.di

import io.imrekaszab.eaplayers.viewmodel.AppViewModel
import io.imrekaszab.eaplayers.viewmodel.PlayerDetailViewModel
import io.imrekaszab.eaplayers.viewmodel.PlayerListViewModel
import org.koin.dsl.module

val viewModelModule = module {
    factory { AppViewModel(get(), get()) }
    factory { PlayerListViewModel(get(), get()) }
    factory { PlayerDetailViewModel(get(), get()) }
}
