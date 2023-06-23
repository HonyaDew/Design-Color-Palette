package com.honey.designcolorpalette.ui.screen.saved.contact

import com.honey.designcolorpalette.base.ViewEvent
import com.honey.designcolorpalette.ui.screen.saved.model.SavedTabs
import com.honey.domain.model.SavedColorScheme

sealed class SavedEvent: ViewEvent {
    data class DeleteColorScheme(val colorScheme: SavedColorScheme) : SavedEvent()
    data class OpenColorScheme(val colorScheme: SavedColorScheme) : SavedEvent()
    object CloseColorScheme : SavedEvent()
    data class ChangeFilterTab(val tab: SavedTabs) : SavedEvent()

}
