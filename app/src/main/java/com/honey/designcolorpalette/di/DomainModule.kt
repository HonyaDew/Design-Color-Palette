package com.honey.designcolorpalette.di

import android.util.Log
import com.honey.designcolorpalette.app.modules
import com.honey.domain.usecase.GetSettingsUseCase
import com.honey.domain.usecase.PutSettingsUseCase
import org.koin.dsl.module

val domainModule = module {

    factory<GetSettingsUseCase> {
        GetSettingsUseCase(settingsRepository = get())
    }

    factory<PutSettingsUseCase> {
        PutSettingsUseCase(settingsRepository = get())
    }

}