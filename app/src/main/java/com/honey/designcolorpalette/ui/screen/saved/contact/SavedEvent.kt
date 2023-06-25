package com.honey.designcolorpalette.ui.screen.saved.contact

import com.honey.designcolorpalette.base.ViewEvent
import com.honey.designcolorpalette.ui.screen.saved.model.SavedTabs
import com.honey.domain.model.CustomColorScheme

sealed class SavedEvent: ViewEvent {
    data class DeleteColorScheme(val colorScheme: CustomColorScheme) : SavedEvent()
    data class OpenColorScheme(val colorScheme: CustomColorScheme) : SavedEvent()
    object CloseColorScheme : SavedEvent()
    data class ChangeFilterTab(val tab: SavedTabs) : SavedEvent()

}
