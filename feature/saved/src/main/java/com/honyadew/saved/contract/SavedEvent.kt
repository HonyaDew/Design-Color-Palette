package com.honyadew.saved.contract

import com.honyadew.model.CustomColorScheme
import com.honyadew.saved.model.SavedTabs

sealed class SavedEvent: com.honyadew.base.ViewEvent {
    data class DeleteColorScheme(val colorScheme: com.honyadew.model.CustomColorScheme) : SavedEvent()
    data class OpenColorScheme(val colorScheme: com.honyadew.model.CustomColorScheme) : SavedEvent()
    object CloseColorScheme : SavedEvent()
    object Refresh : SavedEvent()
    data class ChangeFilterTab(val tab: SavedTabs) : SavedEvent()
    data class SetNewTitle(val title: String, val scheme: CustomColorScheme) : SavedEvent()

}
