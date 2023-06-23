package com.honey.designcolorpalette.ui.screen.saved.contact

import com.honey.designcolorpalette.base.ViewState
import com.honey.designcolorpalette.ui.screen.saved.model.SavedTabs
import com.honey.domain.model.SavedColorScheme

sealed class SavedState : ViewState {
    object Loading : SavedState()
    data class Show(
        val colorsToShow : List<SavedColorScheme>,
        val selectedTab : SavedTabs,
        val openedColorScheme : SavedColorScheme? = null
    ) : SavedState()
}
