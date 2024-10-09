package io.imrekaszab.eaplayers.logger

import co.touchlab.kermit.Logger
import co.touchlab.kermit.StaticConfig
import co.touchlab.kermit.platformLogWriter
import kotlin.reflect.KClass

private val baseLogger by lazy {
    // A class name is used as a tag for the logger, because of R8 obfuscation.
    class EAPlayersApp
    Logger(
        config = StaticConfig(logWriterList = listOf(platformLogWriter())),
        EAPlayersApp::class.simpleName ?: Logger.tag,
    )
}

fun lazyLogger(kClass: KClass<*>) = lazyLogger(kClass.simpleName)

fun logger(tag: String? = null) = tag?.let { baseLogger.withTag(it) } ?: baseLogger

fun lazyLogger(tag: String? = null) = lazy {
    logger(tag)
}
