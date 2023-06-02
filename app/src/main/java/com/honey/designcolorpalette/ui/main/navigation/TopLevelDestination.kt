package com.honey.designcolorpalette.ui.main.navigation

import androidx.annotation.StringRes
import com.honey.designcolorpalette.R

enum class TopLevelDestination(
    @StringRes val titleId: Int
) {
    PALETTE(titleId = R.string.app_name),
    SLIDERS(titleId = R.string.app_name)
}