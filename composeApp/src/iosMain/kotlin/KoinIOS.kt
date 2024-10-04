@file:Suppress("MissingPackageDeclaration")
import co.touchlab.kermit.Logger
import io.imrekaszab.eaplayers.di.initKoin
import io.ktor.client.engine.darwin.Darwin
import kotlinx.cinterop.BetaInteropApi
import kotlinx.cinterop.ObjCClass
import kotlinx.cinterop.ObjCProtocol
import kotlinx.cinterop.getOriginalKotlinClass
import org.koin.core.Koin
import org.koin.core.KoinApplication
import org.koin.core.parameter.parametersOf
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

// Access from Swift to create a logger
@Suppress("unused")
fun Koin.loggerWithTag(tag: String) = get<Logger>(qualifier = null) { parametersOf(tag) }

@OptIn(BetaInteropApi::class)
fun Koin.get(objCClass: ObjCClass): Any? {
    val kClazz = getOriginalKotlinClass(objCClass) ?: return null
    return get(kClazz)
}

@OptIn(BetaInteropApi::class)
fun Koin.get(objCProtocol: ObjCProtocol): Any? {
    val kClazz = getOriginalKotlinClass(objCProtocol) ?: return null
    return get(kClazz)
}
