package io.imrekaszab.eaplayers

data class AppState(
    val error: String? = null,
    val loading: Boolean = false,
    val darkMode: Boolean = false,
)
