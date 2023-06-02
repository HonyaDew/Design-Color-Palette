package com.honey.designcolorpalette.ui.screen.color.contract

import com.honey.designcolorpalette.base.ViewState

sealed class ColorState : ViewState {
    object Loading : ColorState()
}
