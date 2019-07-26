package br.com.exchange.app.application

import android.app.Application
import br.com.data.dataModule
import br.com.exchange.app.appModule
import br.com.network.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin

class ExchangeApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {

            androidContext(baseContext)
            androidLogger()
            androidFileProperties()
            modules(listOf(appModule, networkModule, dataModule))

        }

    }

    override fun onTerminate() {
        super.onTerminate()
        stopKoin()
    }
}