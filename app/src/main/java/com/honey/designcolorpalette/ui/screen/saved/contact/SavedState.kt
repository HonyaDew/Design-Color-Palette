package com.honey.designcolorpalette.ui.screen.saved.contact

import com.honey.designcolorpalette.base.ViewState
import com.honey.designcolorpalette.ui.screen.saved.model.SavedTabs
import com.honey.domain.model.CustomColorScheme

sealed class SavedState : ViewState {
    object Loading : SavedState()
    data class Show(
        val colorsToShow : List<CustomColorScheme>,
        val selectedTab : SavedTabs,
        val openedColorScheme : CustomColorScheme? = null
    ) : SavedState()
}
