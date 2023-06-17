package com.honey.designcolorpalette.ui.screen.saved

import com.honey.designcolorpalette.base.BaseViewModel
import com.honey.designcolorpalette.ui.screen.saved.contact.SavedEffect
import com.honey.designcolorpalette.ui.screen.saved.contact.SavedEvent
import com.honey.designcolorpalette.ui.screen.saved.contact.SavedState

class SavedViewModel : BaseViewModel<SavedEvent, SavedState, SavedEffect>(initialState = SavedState.Loading) {
    override fun obtainEvent(event: SavedEvent) {
        TODO("Not yet implemented")
    }
}