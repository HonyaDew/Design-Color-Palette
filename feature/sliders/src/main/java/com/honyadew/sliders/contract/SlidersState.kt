package com.honyadew.sliders.contract

import androidx.compose.ui.graphics.Color
import com.honyadew.base.ViewState
import com.honyadew.sliders.model.SlidersType

sealed class SlidersState : ViewState {
    object Loading: SlidersState()
    data class Show(
        val type: SlidersType,
        val sliderOne : Float = 0.3f,
        val sliderTwo : Float = 0.5f,
        val sliderThree : Float = 0.7f,
        val sliderAlpha : Float = 1f,
        val colorsToSave: List<com.honyadew.model.ColorInfo> = emptyList()
    ): SlidersState()
}



