package com.honey.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class ColorInfo(
    val value: String,
    val name: String,
    val palette: Palette
)

@Serializable
sealed class Palette() {
    @Serializable
    data class Material(
        val subPalette: ColorOfMaterial
    ): Palette()
    @Serializable
    object FlatUI: Palette()
    @Serializable
    object Social: Palette()
    @Serializable
    object Metro: Palette()
    @Serializable
    object HTML: Palette()
}

@Serializable
enum class ColorOfMaterial(
    val preview: String
) {
    RED(preview = "Color(0.95686275, 0.2627451, 0.21176471, 1.0)"),
    PINK(preview = "Color(0.9137255, 0.11764706, 0.3882353, 1.0)"),
    PURPLE(preview = "Color(0.6117647, 0.15294118, 0.6901961, 1.0)"),
    DEEP_PURPLE(preview = "Color(0.40392157, 0.22745098, 0.7176471, 1.0)"),
    INDIGO(preview = "Color(0.24705882, 0.31764707, 0.70980394, 1.0)"),
    BLUE(preview = "Color(0.12941177, 0.5882353, 0.9529412, 1.0)"),
    LIGHT_BLUE(preview = "Color(0.011764706, 0.6627451, 0.95686275, 1.0)"),
    CYAN(preview = "Color(0.0, 0.7372549, 0.83137256, 1.0)"),
    TEAL(preview = "Color(0.0, 0.5882353, 0.53333336, 1.0)"),
    GREEN(preview = "Color(0.29803923, 0.6862745, 0.3137255, 1.0)"),
    LIGHT_GREEN(preview = "Color(0.54509807, 0.7647059, 0.2901961, 1.0)"),
    LIME(preview = "Color(0.8039216, 0.8627451, 0.22352941, 1.0)"),
    YELLOW(preview = "Color(1.0, 0.92156863, 0.23137255, 1.0)"),
    AMBER(preview = "Color(1.0, 0.75686276, 0.02745098, 1.0)"),
    ORANGE(preview = "Color(1.0, 0.59607846, 0.0, 1.0)"),
    DEEP_ORANGE(preview = "Color(1.0, 0.34117648, 0.13333334, 1.0)"),
    BROWN(preview = "Color(0.4745098, 0.33333334, 0.28235295, 1.0)"),
    GREY(preview = "Color(0.61960787, 0.61960787, 0.61960787, 1.0)"),
    BLUE_GRAY(preview = "Color(0.3764706, 0.49019608, 0.54509807, 1.0)"),
}