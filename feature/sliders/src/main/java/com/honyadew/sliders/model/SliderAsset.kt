package com.honyadew.sliders.model

import androidx.compose.ui.graphics.Color

data class SliderAsset (
    val color: Color? = null,
    val name: String = "",
    val fullName: String = "",
    val steps : Int = 100
)
