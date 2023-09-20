package com.honyadew.saved.contact

import com.honyadew.saved.model.SavedTabs

sealed class SavedEvent: com.honyadew.base.ViewEvent {
    data class DeleteColorScheme(val colorScheme: com.honyadew.model.CustomColorScheme) : SavedEvent()
    data class OpenColorScheme(val colorScheme: com.honyadew.model.CustomColorScheme) : SavedEvent()
    object CloseColorScheme : SavedEvent()
    object Refresh : SavedEvent()
    data class ChangeFilterTab(val tab: SavedTabs) : SavedEvent()

}
