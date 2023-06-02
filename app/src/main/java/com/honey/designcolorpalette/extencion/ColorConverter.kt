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



