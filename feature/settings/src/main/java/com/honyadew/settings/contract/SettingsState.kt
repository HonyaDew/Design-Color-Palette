package com.honyadew.settings.contract

import com.honyadew.base.ViewState

sealed class SettingsState: com.honyadew.base.ViewState {
    object Loading : SettingsState()
    data class Show(val settings: com.honyadew.model.EditableSettings) : SettingsState()
}


