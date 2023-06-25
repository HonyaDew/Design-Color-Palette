package com.honey.designcolorpalette.di

import com.honey.domain.usecase.AddColorToListUseCase
import com.honey.domain.usecase.DeleteColorSchemeUseCase
import com.honey.domain.usecase.FilterColorSchemeUseCase
import com.honey.domain.usecase.GetAllColorSchemeUseCase
import com.honey.domain.usecase.GetColorByPaletteUseCase
import com.honey.domain.usecase.GetSettingsUseCase
import com.honey.domain.usecase.PutSettingsUseCase
import com.honey.domain.usecase.RemoveColorFromListUseCase
import com.honey.domain.usecase.SaveColorSchemeUseCase
import org.koin.dsl.module

val domainModule = module {

    factory<GetSettingsUseCase> {
        GetSettingsUseCase(settingsRepository = get())
    }

    factory<PutSettingsUseCase> {
        PutSettingsUseCase(settingsRepository = get())
    }

    factory<GetColorByPaletteUseCase> {
        GetColorByPaletteUseCase(repository = get())
    }

    factory<GetAllColorSchemeUseCase> {
        GetAllColorSchemeUseCase(savedRepository = get())
    }

    factory<FilterColorSchemeUseCase> {
        FilterColorSchemeUseCase()
    }

    factory<DeleteColorSchemeUseCase> {
        DeleteColorSchemeUseCase(savedRepository = get())
    }

    factory<SaveColorSchemeUseCase> {
        SaveColorSchemeUseCase(savedRepository = get())
    }

    factory<AddColorToListUseCase> {
        AddColorToListUseCase()
    }

    factory<RemoveColorFromListUseCase> {
        RemoveColorFromListUseCase()
    }
}