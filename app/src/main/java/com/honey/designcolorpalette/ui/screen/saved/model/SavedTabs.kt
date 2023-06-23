package com.honey.designcolorpalette.ui.screen.saved.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.honey.designcolorpalette.R
import com.honey.domain.model.ColorSchemeFilters

enum class SavedTabs(
    val filter : ColorSchemeFilters,
    @StringRes val tabNameId: Int,
    @DrawableRes val selectedTabId: Int,
    @DrawableRes val unselectedTabId: Int
) {
    ONE_COLOR (
        filter = ColorSchemeFilters.SingleColor,
        tabNameId = R.string.tab_of_saved_colors,
        selectedTabId = R.drawable.ic_color_drop_select_24,
        unselectedTabId = R.drawable.ic_color_drop_unselect_24
    ),
    SMALL_SCHEME(
        filter = ColorSchemeFilters.UpToFourColors,
        tabNameId = R.string.tab_of_small_color_scheme,
        selectedTabId = R.drawable.ic_custom_palette_select_24,
        unselectedTabId = R.drawable.ic_custom_palette_unselect_24
    ),
    BIG_SCHEME(
        filter = ColorSchemeFilters.MultiColors,
        tabNameId = R.string.tab_of_big_color_cheme,
        selectedTabId = R.drawable.ic_big_custom_palette_select_24 ,
        unselectedTabId = R.drawable.ic_big_custom_palette_unselect_24
    )
}