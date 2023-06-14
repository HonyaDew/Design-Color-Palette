package com.honey.designcolorpalette.ui.screen.sliders.contract

import com.honey.designcolorpalette.base.ViewState
import java.util.concurrent.Flow

sealed class SlidersState : ViewState {
    object Loading: SlidersState()
    data class Show(val slidersValues: SlidersType)
}

sealed class SlidersType {
    data class SliderRGBA(
        val red: Int, // 0..255
        val green : Int, // 0..255
        val blue: Int, // 0..255
        val alpha: Float //0..1
    ) : SlidersType()
    data class SliderHSV(
        val hue : Int, //0..360
        val saturation: Float, //0..1
        val value: Float //0..1
    )
}
