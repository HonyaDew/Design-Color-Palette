package com.honyadew.designcolorpalette.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.honyadew.domain.repository.PaletteRepository
import com.honyadew.domain.repository.SavedRepository
import com.honyadew.domain.repository.SettingsRepository
import com.honyadew.palette.PaletteRepositoryImpl
import com.honyadew.saved.SavedRepositoryImpl
import com.honyadew.settings.SettingsRepositoryImpl
import com.honyadew.database.Constance.DATABASE_NAME
import com.honyadew.database.DcpDatabase
import com.honyadew.database.dao.SavedDao
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

    single<DcpDatabase> {
        Room.databaseBuilder(
            context = androidContext(),
            klass = DcpDatabase::class.java,
            name = DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }

    single<SavedDao> {
        get<DcpDatabase>().savedDao()
    }

    single<SavedRepository> {
        SavedRepositoryImpl(
            dao = get()
        )
    }
}