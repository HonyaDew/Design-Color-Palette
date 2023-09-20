package com.honyadew.saved.contact

import com.honyadew.base.ViewState
import com.honyadew.saved.model.SavedTabs

sealed class SavedState : ViewState {
    object Loading : SavedState()
    object NoObjects : SavedState()
    data class Show(
        val colorsToShow : List<com.honyadew.model.CustomColorScheme>,
        val selectedTab : SavedTabs,
        val openedColorScheme : com.honyadew.model.CustomColorScheme? = null
    ) : SavedState()
}
