package com.honey.designcolorpalette.ui.screen.dialog.contract

import com.honey.designcolorpalette.base.ViewState
import com.honey.domain.model.EditableSettings

sealed class SettingsState: ViewState{
    object Loading : SettingsState()
    data class Show(val settings: EditableSettings) : SettingsState()
}


