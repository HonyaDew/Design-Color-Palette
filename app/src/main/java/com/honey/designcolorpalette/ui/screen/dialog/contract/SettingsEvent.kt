package com.honey.designcolorpalette.ui.screen.dialog.contract

import com.honey.designcolorpalette.base.ViewEvent
import com.honey.domain.model.Palette
import com.honey.domain.model.ThemeConfig

sealed class SettingsEvent : ViewEvent {
    data class SelectPalette(val palette: Palette) : SettingsEvent()
    data class SelectTheme(val theme: ThemeConfig) : SettingsEvent()
}
