package com.honyadew.image.contract

import android.graphics.Bitmap
import androidx.compose.ui.graphics.Color
import com.honyadew.base.ViewEvent
import com.honyadew.model.ColorInfo

sealed class ImageEvent : ViewEvent {
    data class SelectColor(val color: Color) : ImageEvent()
    data class RemoveFromToSave(val color: ColorInfo) : ImageEvent()
    data class MoveToSave(val color: ColorInfo) : ImageEvent()
    data class SaveColorScheme(val colorScheme: com.honyadew.model.CustomColorScheme): ImageEvent()
    data class SetExtractedColors(val extractedColors: List<ColorInfo>) : ImageEvent()
    data class SetBitmap(val bitmap: Bitmap) : ImageEvent()
}
