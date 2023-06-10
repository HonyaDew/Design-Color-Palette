package com.honey.designcolorpalette.di

import android.util.Log
import com.honey.designcolorpalette.ui.screen.color.ColorViewModel
import com.honey.designcolorpalette.ui.screen.dialog.SettingsViewModel
import com.honey.designcolorpalette.ui.screen.palette.PaletteViewModel
import com.honey.designcolorpalette.ui.screen.sliders.SlidersViewModel
import com.honey.domain.usecase.GetSettingsUseCase
import org.koin.dsl.module

val appModule = module {
    factory<ColorViewModel> {
        ColorViewModel()
    }

    factory<SlidersViewModel> {
        SlidersViewModel()
    }

    factory<PaletteViewModel> {
        PaletteViewModel(
            getSettings = get()
        )
    }
    factory<SettingsViewModel>{
        Log.d("MyLog","SettingsViewModel init")
        SettingsViewModel(
            getSettings = get(),
            putSettings = get()
        )
    }
}