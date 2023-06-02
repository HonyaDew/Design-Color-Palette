package com.honey.designcolorpalette.ui.screen.palette

import com.honey.designcolorpalette.base.BaseViewModel
import com.honey.designcolorpalette.ui.screen.palette.contract.PaletteEffect
import com.honey.designcolorpalette.ui.screen.palette.contract.PaletteEvent
import com.honey.designcolorpalette.ui.screen.palette.contract.PaletteState

class PaletteViewModel : BaseViewModel<PaletteEvent, PaletteState, PaletteEffect>(initialState =  PaletteState.Loading) {
    override fun obtainEvent(event: PaletteEvent) {

    }
}