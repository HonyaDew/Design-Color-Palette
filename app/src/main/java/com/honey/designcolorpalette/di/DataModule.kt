package com.honey.designcolorpalette.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.honey.saved.datasource.internal.Constance
import com.honey.domain.repository.PaletteRepository
import com.honey.domain.repository.SavedRepository
import com.honey.domain.repository.SettingsRepository
import com.honey.palette.PaletteRepositoryImpl
import com.honey.saved.SavedRepositoryImpl
import com.honey.saved.datasource.internal.SavedDao
import com.honey.saved.datasource.internal.SavedDatabase
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

    single<PaletteRepository>{
        PaletteRepositoryImpl()
    }

    single<SavedDatabase> {
        Room.databaseBuilder(
            context = androidContext(),
            klass = SavedDatabase::class.java,
            name = Constance.DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }

    single<SavedDao> {
        get<SavedDatabase>().savedDao()
    }

    single<SavedRepository> {
        SavedRepositoryImpl(
            dao = get()
        )
    }

}