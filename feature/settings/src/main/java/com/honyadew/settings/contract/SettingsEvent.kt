package com.honyadew.settings.contract

import com.honyadew.base.ViewEvent
import com.honyadew.model.Palette
import com.honyadew.model.ThemeConfig

sealed class SettingsEvent : ViewEvent {
    data class SelectPalette(val palette: Palette) : SettingsEvent()
    data class SelectTheme(val theme: ThemeConfig) : SettingsEvent()
}
