package com.honyadew.palette.extencion

import androidx.compose.ui.graphics.Color


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