package com.honyadew.sliders.model

import androidx.compose.ui.graphics.Color

enum class SlidersType (
    val assetOfFirst : SliderAsset,
    val assetOfSecond : SliderAsset,
    val assetOfThird : SliderAsset,
    val assetOfAlpha : SliderAsset,
) {
    RGB(
        assetOfFirst = SliderAsset(color = Color.Red, name = "R", steps = 255),
        assetOfSecond = SliderAsset(color = Color.Green, name = "G", steps = 255),
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