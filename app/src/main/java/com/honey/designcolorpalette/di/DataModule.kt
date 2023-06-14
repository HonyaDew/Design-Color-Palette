package com.honey.designcolorpalette.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.honey.color.ColorRepositoryImpl
import com.honey.color.datasource.internal.ColorDao
import com.honey.color.datasource.internal.ColorDatabase
import com.honey.color.datasource.internal.Constance
import com.honey.domain.repository.ColorRepository
import com.honey.domain.repository.PaletteRepository
import com.honey.domain.repository.SettingsRepository
import com.honey.palette.PaletteRepositoryImpl
import com.honey.settings.SettingsRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import kotlin.math.sin

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

    single<ColorDatabase> {
        Room.databaseBuilder(
            context = androidContext(),
            klass = ColorDatabase::class.java,
            name = Constance.DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }

    single<ColorDao> {
        get<ColorDatabase>().colorDao()
    }

    single <ColorRepository>{
        ColorRepositoryImpl(
            dao = get()
        )
    }
}