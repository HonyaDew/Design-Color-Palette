package com.honey.designcolorpalette.ui.screen.sliders.contract

import com.honey.designcolorpalette.base.ViewState

sealed class SlidersState : ViewState {
    object Loading: SlidersState()
}