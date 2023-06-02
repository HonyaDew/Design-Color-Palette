package com.honey.designcolorpalette.ui.screen.palette.contract

import com.honey.designcolorpalette.base.ViewState

sealed class PaletteState : ViewState {
    object Loading : PaletteState()
}