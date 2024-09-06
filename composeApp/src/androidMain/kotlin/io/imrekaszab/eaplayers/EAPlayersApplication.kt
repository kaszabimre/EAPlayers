package io.imrekaszab.eaplayers

import android.app.Application
import io.imrekaszab.eaplayers.di.initDi

class EAPlayersApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        val baseUrl = BuildConfig.BASE_PATH
        initDi(applicationContext, baseUrl)
    }
}
