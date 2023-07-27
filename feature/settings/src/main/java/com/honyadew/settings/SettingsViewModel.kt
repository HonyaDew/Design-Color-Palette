package com.honyadew.settings

import com.honyadew.base.BaseViewModel
import com.honyadew.settings.contract.SettingsEffect
import com.honyadew.settings.contract.SettingsEvent
import com.honyadew.settings.contract.SettingsState
import com.honey.domain.usecase.GetSettingsUseCase
import com.honey.domain.usecase.PutSettingsUseCase

class SettingsViewModel(
    private val getSettings: GetSettingsUseCase,
    private val putSettings: PutSettingsUseCase
) : BaseViewModel<SettingsEvent, SettingsState, SettingsEffect>(initialState = SettingsState.Loading) {

    init {
        viewState = SettingsState.Show(settings = getSettings.invoke())
    }

    override fun obtainEvent(event: SettingsEvent) {
        when(val state = viewState){
            is SettingsState.Loading -> {reduce(event,state)}
            is SettingsState.Show -> {reduce(event,state)}
        }
    }

    private fun reduce(event: SettingsEvent, state: SettingsState.Loading){

    }

    private fun reduce(event: SettingsEvent, state: SettingsState.Show){
        when(event){
            is SettingsEvent.SelectPalette -> {
                viewState = SettingsState.Show(state.settings.copy(palette = event.palette))
                putSettings.invoke(settings = state.settings.copy(palette = event.palette))
            }
            is SettingsEvent.SelectTheme -> {
                viewState = SettingsState.Show(state.settings.copy(themeConfig = event.theme))
                putSettings.invoke(settings = state.settings.copy(themeConfig = event.theme))
            }
        }
    }
}