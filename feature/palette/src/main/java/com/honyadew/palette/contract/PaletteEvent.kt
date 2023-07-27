package com.honyadew.palette.contract

import com.honyadew.base.ViewEvent

sealed class PaletteEvent: com.honyadew.base.ViewEvent {
    data class SelectSubPalette (val palette: com.honyadew.model.Palette) : PaletteEvent()
    object UpdatePalette : PaletteEvent()
}