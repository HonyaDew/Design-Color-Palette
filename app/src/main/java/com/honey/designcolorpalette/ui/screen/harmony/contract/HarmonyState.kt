package com.honey.designcolorpalette.ui.screen.harmony.contract

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import com.honey.designcolorpalette.R
import com.honey.designcolorpalette.base.ViewState
import com.honyadew.harmony_color_picker.model.ColorHarmonyMode

sealed class HarmonyState : ViewState {
    object Loading : HarmonyState()
    data class Show(
        val initialPageIndex : Int = 0,
        val harmoniesValue : List<Color> = List(HarmonyMode.values().size) { Color.Red },
        val hexAsCopyMode : Boolean = true
    ) : HarmonyState()
}

enum class HarmonyMode(
    @DrawableRes val iconResId : Int,
    @StringRes val nameResId : Int,
    val colorHarmonyMode: ColorHarmonyMode
){

    //TODO(add a normal icons)
    COMPLIMENTARY(
        iconResId = R.drawable.ic_complimentary_24,
        nameResId = R.string.complimentary,
        colorHarmonyMode = ColorHarmonyMode.COMPLEMENTARY
    ),
    SPLIT_COMPLIMENTARY(
        iconResId = R.drawable.ic_split_complimentary_24,
        nameResId = R.string.split_complimentary,
        colorHarmonyMode = ColorHarmonyMode.SPLIT_COMPLEMENTARY
    ),
    ANALOGOUS(
        iconResId = R.drawable.ic_analogus_24,
        nameResId = R.string.analogus,
        colorHarmonyMode = ColorHarmonyMode.ANALOGOUS
    ),
    TRIADIC(
        iconResId = R.drawable.ic_triadic_24,
        nameResId = R.string.triadic,
        colorHarmonyMode = ColorHarmonyMode.TRIADIC
    ),
    TETRADIC(
        iconResId = R.drawable.ic_tetradic_24,
        nameResId = R.string.tetradic,
        colorHarmonyMode = ColorHarmonyMode.TETRADIC
    )
}
