package com.honey.designcolorpalette.model

data class ColorInfo(
    val value: String,
    val name: String,
    val palette: Palette
)

sealed class Palette {
    object Material: Palette()
    object FlatUI: Palette()
}
