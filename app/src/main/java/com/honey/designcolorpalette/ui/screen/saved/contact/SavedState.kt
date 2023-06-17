package com.honey.designcolorpalette.ui.screen.saved.contact

import com.honey.designcolorpalette.base.ViewState

sealed class SavedState : ViewState {
    object Loading : SavedState()
}
