package com.honey.designcolorpalette.ui.screen.sliders.contract

import androidx.compose.ui.graphics.Color
import com.honey.designcolorpalette.base.ViewState

sealed class SlidersState : ViewState {
    object Loading: SlidersState()
    data class Show(
        val type: SlidersType,
        val sliderOne : Float = 0.3f,
        val sliderTwo : Float = 0.5f,
        val sliderThree : Float = 0.7f,
        val sliderAlpha : Float = 1f,
    ): SlidersState()
}

enum class SlidersType (
    val assetOfFirst : SliderAsset,
    val assetOfSecond : SliderAsset,
    val assetOfThird : SliderAsset,
    val assetOfAlpha : SliderAsset,
) {
    RGB(
        assetOfFirst = SliderAsset(color = Color.Red, name = "R", steps = 360),
        assetOfSecond = SliderAsset(color = Color.Green, name = "G", steps = 100),
        assetOfThird = SliderAsset(color = Color.Blue, name = "B", steps = 255),
        assetOfAlpha = SliderAsset(color = null, name = "A", steps = 100)
    ),

    HSV(
        assetOfFirst = SliderAsset(color = Color.Yellow, name = "H", steps = 360),
        assetOfSecond = SliderAsset(color = Color.Yellow, name = "S", steps = 100),
        assetOfThird = SliderAsset(color = Color.Yellow, name = "V", steps = 100),
        assetOfAlpha = SliderAsset(color = null, name = "A", steps = 100)
    )

}

data class SliderAsset (
    val color: Color? = null,
    val name: String = "",
    val steps : Int = 100
)


