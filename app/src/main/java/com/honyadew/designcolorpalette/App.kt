package com.honyadew.designcolorpalette

import android.app.Application
import com.honyadew.designcolorpalette.di.appModule
import com.honyadew.designcolorpalette.di.dataModule
import com.honyadew.designcolorpalette.di.domainModule
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