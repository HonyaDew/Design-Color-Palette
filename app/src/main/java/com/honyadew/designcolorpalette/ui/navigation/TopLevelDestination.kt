package com.honyadew.designcolorpalette.ui.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.honyadew.designcolorpalette.R

enum class TopLevelDestination(
    @StringRes val titleId: Int,
    @DrawableRes val selectedButtonId: Int,
    @DrawableRes val unSelectedButtonId: Int,
    @StringRes val iconTextId: Int
) {
    PALETTE(
        titleId = R.string.app_name,
        selectedButtonId = R.drawable.ic_palette_24,
        unSelectedButtonId = R.drawable.ic_palette_outlined_24,
        iconTextId = R.string.palette
    ),
    SLIDERS(
        titleId = R.string.sliders ,
        selectedButtonId = R.drawable.ic_sliders_thick_24,
        unSelectedButtonId = R.drawable.ic_sliders_thin_24,
        iconTextId = R.string.sliders
    ),
    SAVED(
        titleId = R.string.saved,
        selectedButtonId = R.drawable.ic_saved_24,
        unSelectedButtonId = R.drawable.ic_saved_outlined_24,
        iconTextId = R.string.saved
    ),
    HARMONY(
        titleId = R.string.harmony,
        selectedButtonId = R.drawable.ic_harmony_24,
        unSelectedButtonId = R.drawable.ic_harmony_outlined_24,
        iconTextId = R.string.harmony
    ),
    IMAGE(
        titleId = R.string.image,
        selectedButtonId = R.drawable.ic_image_24,
        unSelectedButtonId = R.drawable.ic_image_outlined_24,
        iconTextId = R.string.image
    )
}