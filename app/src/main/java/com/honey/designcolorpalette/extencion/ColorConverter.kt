package com.honey.designcolorpalette.extencion

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.graphics.toColorInt


fun Color.string(): String {
    return "Color(${red}, ${green}, ${blue}, ${alpha})"
}


fun String.color(): Color {
    val components = this
        .removePrefix("Color(")
        .removeSuffix(")")
        .split(",")
        .map { it.trim().toFloat() }

    return Color(components[0], components[1], components[2], components[3])
}

fun Color.toHexString(): String {
    return String.format("#%08X", this.toArgb())
}

fun Color.saturation(): String {
    val red = this.red
    val green = this.green
    val blue = this.blue

    val maxChannel = maxOf(red, green, blue)
    val minChannel = minOf(red, green, blue)

    val saturation = (maxChannel - minChannel) / maxChannel.toFloat() * 100

    return saturation.toInt().toString()
}



