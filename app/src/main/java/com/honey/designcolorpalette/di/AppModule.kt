package com.honey.designcolorpalette.di

import android.util.Log
import com.honey.designcolorpalette.ui.screen.dialog.SettingsViewModel
import com.honey.designcolorpalette.ui.screen.palette.PaletteViewModel
import com.honey.designcolorpalette.ui.screen.saved.SavedViewModel
import com.honey.designcolorpalette.ui.screen.sliders.SlidersViewModel
import com.honey.domain.usecase.GetSettingsUseCase
import org.koin.dsl.module

val appModule = module {


    factory<SlidersViewModel> {
        SlidersViewModel()
    }

    factory<PaletteViewModel> {
        PaletteViewModel(
            getSettings = get(),
            putSettings = get(),
            getColorsByPalette = get()
        )
    }
    factory<SettingsViewModel>{
        SettingsViewModel(
            getSettings = get(),
            putSettings = get()
        )
    }
    factory<SavedViewModel> {
        SavedViewModel(
            getAllSavedColorScheme = get(),
            filterColorScheme = get()
        )
    }
}