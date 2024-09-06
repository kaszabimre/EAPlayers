package internal

import io.imrekaszab.eaplayers.di.initKoin
import io.ktor.client.engine.darwin.Darwin
import kotlinx.cinterop.BetaInteropApi
import kotlinx.cinterop.ObjCClass
import kotlinx.cinterop.ObjCProtocol
import kotlinx.cinterop.getOriginalKotlinClass
import org.koin.core.Koin
import org.koin.core.KoinApplication
import org.koin.dsl.module

fun initKoinIos(
    doOnStartup: () -> Unit,
    baseUrl: String
): KoinApplication = initKoin(
    module {
        single { doOnStartup }
        single { Darwin.create() }
    },
    baseUrl
)

@OptIn(BetaInteropApi::class)
fun Koin.get(objCClass: ObjCClass): Any {
    val kClazz = getOriginalKotlinClass(objCClass) ?: error("No Kotlin class found for $objCClass")
    return get(kClazz)
}

@OptIn(BetaInteropApi::class)
fun Koin.get(objCProtocol: ObjCProtocol): Any {
    val kClazz =
        getOriginalKotlinClass(objCProtocol) ?: error("No Kotlin class found for $objCProtocol")
    return get(kClazz)
}
