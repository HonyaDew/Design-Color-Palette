package com.honey.designcolorpalette.di

import android.content.Context
import android.content.SharedPreferences
import com.honey.domain.repository.SettingsRepository
import com.honey.settings.SettingsRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {

    single<SettingsRepository> {
        SettingsRepositoryImpl(sharedPreferences = get())
    }

    single<SharedPreferences> {
        androidContext().getSharedPreferences("default", Context.MODE_PRIVATE)
    }
}