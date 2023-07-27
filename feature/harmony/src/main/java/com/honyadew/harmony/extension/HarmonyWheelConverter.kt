package com.honyadew.harmony.extension

import androidx.compose.ui.graphics.Color
import com.honyadew.harmony.contract.HarmonyMode
import com.honyadew.harmony_color_picker.model.HsvColor


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



