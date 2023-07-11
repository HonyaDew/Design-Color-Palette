package com.honey.designcolorpalette.extencion

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.honey.designcolorpalette.ui.screen.harmony.contract.HarmonyMode
import com.honyadew.harmony_color_picker.model.HsvColor
import kotlin.math.roundToInt


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

fun Color.toStringRGBA(): String {
    return (red * 255).roundToInt().toString() + "," +
            (green* 255).roundToInt().toString() + "," +
            (blue* 255).roundToInt().toString() + "," +
            (alpha* 100).roundToInt().toString()
}

fun Color.toStringRGB(): String {
    return (red * 255).roundToInt().toString() + "," +
            (green* 255).roundToInt().toString() + "," +
            (blue* 255).roundToInt().toString()
}

fun Color.getFullHarmony(harmony: HarmonyMode): List<Color>{
    val hsvColor = HsvColor.from(this)
    val hsvAdditional = when (harmony){
        HarmonyMode.COMPLEMENTARY -> {hsvColor.getComplementaryColor()}
        HarmonyMode.SPLIT_COMPLEMENTARY -> {hsvColor.getSplitComplementaryColors()}
        HarmonyMode.ANALOGOUS -> {hsvColor.getAnalagousColors()}
        HarmonyMode.TRIADIC -> {hsvColor.getTriadicColors()}
        HarmonyMode.TETRADIC -> {hsvColor.getTetradicColors()}
    }
    val additional = hsvAdditional.map { it.toColor() }
    return listOf(this) + additional
}



