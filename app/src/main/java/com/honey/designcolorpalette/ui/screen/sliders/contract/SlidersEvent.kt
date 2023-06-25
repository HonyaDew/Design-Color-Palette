package com.honey.designcolorpalette.ui.screen.sliders.contract

import android.app.slice.Slice
import com.honey.designcolorpalette.base.ViewEvent
import com.honey.domain.model.ColorInfo
import com.honey.domain.model.ColorSchemeSource
import com.honey.domain.model.CustomColorScheme

sealed class SlidersEvent : ViewEvent {
    data class SetFirstSliderValue(val newValue: Float) : SlidersEvent()
    data class SetSecondSliderValue(val newValue: Float) : SlidersEvent()
    data class SetThirdSliderValue(val newValue: Float) : SlidersEvent()
    data class SetAlphaSliderValue(val newValue: Float) : SlidersEvent()
    object SelectRGB : SlidersEvent()
    object SelectHSV : SlidersEvent()
    data class SaveColorScheme(val colorScheme: CustomColorScheme) : SlidersEvent()
    data class AddColorToSaveList(val color: ColorInfo) : SlidersEvent()
    data class RemoveColorFromToSaveList(val color: ColorInfo) : SlidersEvent()
}