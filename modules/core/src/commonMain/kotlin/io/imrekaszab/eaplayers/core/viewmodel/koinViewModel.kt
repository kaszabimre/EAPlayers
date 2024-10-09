package io.imrekaszab.eaplayers.core.viewmodel

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import org.koin.compose.koinInject

@Composable
inline fun <reified VM : ViewModel> koinViewModel(): VM {
    val injectedViewModel = koinInject<VM>()
    return viewModel { injectedViewModel }
}