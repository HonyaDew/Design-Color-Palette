package com.honyadew.designcolorpalette.di

import com.honyadew.domain.usecase.DeleteColorSchemeUseCase
import com.honyadew.domain.usecase.GetAllColorSchemeUseCase
import com.honyadew.domain.usecase.GetColorByPaletteUseCase
import com.honyadew.domain.usecase.GetSettingsUseCase
import com.honyadew.domain.usecase.PutSettingsUseCase
import com.honyadew.domain.usecase.SaveColorSchemeUseCase
import org.koin.dsl.module

val domainModule = module {

    factory<GetSettingsUseCase> {
        GetSettingsUseCase(settingsRepository = get())
    }

    factory<PutSettingsUseCase> {
        PutSettingsUseCase(settingsRepository = get())
    }

    factory<GetColorByPaletteUseCase> {
        GetColorByPaletteUseCase(paletteRepository = get())
    }

    factory<GetAllColorSchemeUseCase> {
        GetAllColorSchemeUseCase(savedRepository = get())
    }

    factory<DeleteColorSchemeUseCase> {
        DeleteColorSchemeUseCase(savedRepository = get())
    }

    factory<SaveColorSchemeUseCase> {
        SaveColorSchemeUseCase(savedRepository = get())
    }
}