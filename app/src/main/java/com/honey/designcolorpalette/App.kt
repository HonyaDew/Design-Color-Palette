package com.honey.designcolorpalette

import android.app.Application
import com.honey.designcolorpalette.di.appModule
import com.honey.designcolorpalette.di.dataModule
import com.honey.designcolorpalette.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidLogger(level = Level.ERROR)
            androidContext(this@App)
            modules(modules)
        }
    }
}
val modules = listOf(
    appModule,
    domainModule,
    dataModule
)