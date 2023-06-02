package com.honey.designcolorpalette.ui.screen.color

import com.honey.designcolorpalette.base.BaseViewModel
import com.honey.designcolorpalette.ui.screen.color.contract.ColorEffect
import com.honey.designcolorpalette.ui.screen.color.contract.ColorEvent
import com.honey.designcolorpalette.ui.screen.color.contract.ColorState

class ColorViewModel : BaseViewModel<ColorEvent, ColorState, ColorEffect>(initialState = ColorState.Loading) {

    override fun obtainEvent(event: ColorEvent) {

    }
}