package com.honey.designcolorpalette.ui.screen.dialog.contract

import com.honey.designcolorpalette.base.ViewEvent
import com.honey.domain.model.Palette

sealed class SettingsEvent : ViewEvent {
    data class SelectPalette(val palette: Palette) : SettingsEvent()
}
