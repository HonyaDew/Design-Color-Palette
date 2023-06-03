package com.honey.designcolorpalette.di

import com.honey.designcolorpalette.ui.screen.color.ColorViewModel
import com.honey.designcolorpalette.ui.screen.palette.PaletteViewModel
import com.honey.designcolorpalette.ui.screen.sliders.SlidersViewModel
import org.koin.dsl.module

val appModule = module {
    factory<ColorViewModel> {
        ColorViewModel()
    }

    factory<SlidersViewModel> {
        SlidersViewModel()
    }

    factory<PaletteViewModel> {
        PaletteViewModel()
    }
}