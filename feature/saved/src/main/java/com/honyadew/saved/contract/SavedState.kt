package com.honyadew.saved.contract

import com.honyadew.base.ViewState
import com.honyadew.model.CustomColorScheme
import com.honyadew.saved.model.SavedTabs

sealed class SavedState : ViewState {
    object Loading : SavedState()
    object NoObjects : SavedState()
    data class Show(
        val allSchemes : List<CustomColorScheme>,
        val selectedTab : SavedTabs,
        val openedColorScheme : CustomColorScheme? = null
    ) : SavedState()
}
