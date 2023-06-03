package com.honey.designcolorpalette.ui.main.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.honey.designcolorpalette.R

enum class TopLevelDestination(
    @StringRes val titleId: Int,
    @DrawableRes val selectedButtonId: Int,
    @DrawableRes val unSelectedButtonId: Int,
    @StringRes val iconTextId: Int
) {
    PALETTE(
        titleId = R.string.app_name,
        selectedButtonId = R.drawable.ic_palette,
        unSelectedButtonId = R.drawable.ic_palette_outlined,
        iconTextId = R.string.palette
    ),
    SLIDERS(
        titleId = R.string.sliders ,
        selectedButtonId = R.drawable.ic_palette,
        unSelectedButtonId = R.drawable.ic_palette_outlined,
        iconTextId = R.string.sliders
    )
}