package io.imrekaszab.eaplayers.di

import co.touchlab.kermit.Logger
import kotlinx.cinterop.BetaInteropApi
import kotlinx.cinterop.ObjCClass
import kotlinx.cinterop.ObjCProtocol
import kotlinx.cinterop.getOriginalKotlinClass
import org.koin.core.Koin
import org.koin.core.KoinApplication
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module

fun initKoinIos(baseUrl: String): KoinApplication = initKoin(
    module {},
    baseUrl
)

// Access from Swift to create a logger
@Suppress("unused")
fun Koin.loggerWithTag(tag: String) = get<Logger>(qualifier = null) { parametersOf(tag) }

@OptIn(BetaInteropApi::class)
internal fun Koin.get(objCClass: ObjCClass): Any {
    val kClazz = getOriginalKotlinClass(objCClass) ?: error("No Kotlin class found for $objCClass")
    return get(kClazz)
}

@OptIn(BetaInteropApi::class)
internal fun Koin.get(objCProtocol: ObjCProtocol): Any {
    val kClazz =
        getOriginalKotlinClass(objCProtocol) ?: error("No Kotlin class found for $objCProtocol")
    return get(kClazz)
}
