package com.honey.designcolorpalette.ui.screen.image.contract

import androidx.compose.ui.graphics.Color
import com.honey.designcolorpalette.base.ViewEvent
import com.honey.domain.model.ColorInfo
import com.honey.domain.model.CustomColorScheme

sealed class ImageEvent : ViewEvent {
    data class SelectColor(val color: Color) : ImageEvent()
    data class RemoveFromToSave(val color: ColorInfo) : ImageEvent()
    data class MoveToSave(val color: ColorInfo) : ImageEvent()
    data class SaveColorScheme(val colorScheme: CustomColorScheme): ImageEvent()
}
