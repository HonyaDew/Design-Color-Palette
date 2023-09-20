package com.honey.designcolorpalette.di

import com.honey.designcolorpalette.MainActivityViewModel
import com.honyadew.palette.PaletteViewModel
import com.honyadew.sliders.SlidersViewModel
import org.koin.dsl.module

val appModule = module {

    factory<SlidersViewModel> {
        SlidersViewModel(
            saveColorScheme = get()
        )
    }

    factory<com.honyadew.palette.PaletteViewModel> {
        PaletteViewModel(
            getSettings = get(),
            putSettings = get(),
            getColorsByPalette = get()
        )
    }
    factory<com.honyadew.settings.SettingsViewModel>{
        com.honyadew.settings.SettingsViewModel(
            getSettings = get(),
            putSettings = get()
        )
    }
    factory<com.honyadew.saved.SavedViewModel> {
        com.honyadew.saved.SavedViewModel(
            getAllSavedColorScheme = get(),
            deleteColorScheme = get()
        )
    }
    factory<com.honyadew.harmony.HarmonyViewModel> {
        com.honyadew.harmony.HarmonyViewModel(
            saveColorScheme = get()
        )
    }
    factory<com.honyadew.image.ImageViewModel> {
        com.honyadew.image.ImageViewModel(
            saveColorScheme = get()
        )
    }
    factory<MainActivityViewModel> {
        MainActivityViewModel(
            getSettings = get()
        )
    }
}