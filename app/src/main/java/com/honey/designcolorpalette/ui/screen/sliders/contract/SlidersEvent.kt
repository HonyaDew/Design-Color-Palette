package com.honey.designcolorpalette.ui.screen.sliders.contract

import com.honey.designcolorpalette.base.ViewEvent

sealed class SlidersEvent : ViewEvent {
    data class SetFirstSliderValue(val newValue: Float) : SlidersEvent()
    data class SetSecondSliderValue(val newValue: Float) : SlidersEvent()
    data class SetThirdSliderValue(val newValue: Float) : SlidersEvent()
    data class SetAlphaSliderValue(val newValue: Float) : SlidersEvent()
    object SelectRGB : SlidersEvent()
    object SelectHSV : SlidersEvent()
}