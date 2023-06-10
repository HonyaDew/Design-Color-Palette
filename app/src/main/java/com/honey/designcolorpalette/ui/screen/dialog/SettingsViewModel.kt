package com.honey.designcolorpalette.ui.screen.dialog

import com.honey.designcolorpalette.base.BaseViewModel
import com.honey.designcolorpalette.ui.screen.dialog.contract.SettingsEffect
import com.honey.designcolorpalette.ui.screen.dialog.contract.SettingsEvent
import com.honey.designcolorpalette.ui.screen.dialog.contract.SettingsState
import com.honey.domain.model.EditableSettings
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
                viewState = SettingsState.Show(EditableSettings(palette = event.palette))
                putSettings.invoke(settings = EditableSettings(palette = event.palette))
            }
            else -> {}
        }
    }
}