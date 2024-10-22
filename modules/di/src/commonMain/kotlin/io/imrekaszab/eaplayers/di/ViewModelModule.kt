package io.imrekaszab.eaplayers.di

import io.imrekaszab.eaplayers.playerdetails.viewmodel.PlayerDetailViewModel
import io.imrekaszab.eaplayers.playerlist.viewmodel.PlayerListViewModel
import org.koin.dsl.module

val viewModelModule = module {
    factory { PlayerListViewModel(get(), get()) }
    factory { PlayerDetailViewModel(get(), get()) }
}
