package com.honyadew.sliders.contract

import com.honyadew.base.ViewEvent

sealed class SlidersEvent : ViewEvent {
    data class SetFirstSliderValue(val newValue: Float) : SlidersEvent()
    data class SetSecondSliderValue(val newValue: Float) : SlidersEvent()
    data class SetThirdSliderValue(val newValue: Float) : SlidersEvent()
    data class SetAlphaSliderValue(val newValue: Float) : SlidersEvent()
    object SelectRGB : SlidersEvent()
    object SelectHSV : SlidersEvent()
    data class SaveColorScheme(val colorScheme: com.honyadew.model.CustomColorScheme) : SlidersEvent()
    data class AddColorToSaveList(val color: com.honyadew.model.ColorInfo) : SlidersEvent()
    data class RemoveColorFromToSaveList(val color: com.honyadew.model.ColorInfo) : SlidersEvent()
}