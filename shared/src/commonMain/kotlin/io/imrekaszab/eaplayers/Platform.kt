package io.imrekaszab.eaplayers

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
