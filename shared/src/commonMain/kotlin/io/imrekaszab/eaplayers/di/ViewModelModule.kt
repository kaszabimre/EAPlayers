package io.imrekaszab.eaplayers.di

import io.imrekaszab.eaplayers.ui.viewmodel.PlayerListViewModel
import io.imrekaszab.eaplayers.viewmodel.PlayerDetailViewModel
import org.koin.dsl.module

val viewModelModule = module {
    factory { PlayerListViewModel(get(), get()) }
    factory { PlayerDetailViewModel(get(), get()) }
}
