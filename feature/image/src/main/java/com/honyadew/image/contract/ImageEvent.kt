package com.honyadew.image.contract

import android.graphics.Bitmap
import androidx.compose.ui.graphics.Color
import com.honyadew.base.ViewEvent

sealed class ImageEvent : com.honyadew.base.ViewEvent {
    data class SelectColor(val color: Color) : ImageEvent()
    data class RemoveFromToSave(val color: com.honyadew.model.ColorInfo) : ImageEvent()
    data class MoveToSave(val color: com.honyadew.model.ColorInfo) : ImageEvent()
    data class SaveColorScheme(val colorScheme: com.honyadew.model.CustomColorScheme): ImageEvent()
    data class SetExtractedColors(val extractedColors: List<com.honyadew.model.ExtractColor>) : ImageEvent()
    data class SetBitmap(val bitmap: Bitmap) : ImageEvent()
}
