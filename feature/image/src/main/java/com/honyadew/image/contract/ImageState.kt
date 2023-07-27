package com.honyadew.image.contract

import android.graphics.Bitmap
import androidx.compose.ui.graphics.Color
import com.honyadew.base.ViewState

sealed class ImageState : com.honyadew.base.ViewState {
    object Loading : ImageState()
    data class Show(
        val selectedColor: Color,
        val colorsToSave : List<com.honyadew.model.ColorInfo> = emptyList(),
        val extractedColors : List<com.honyadew.model.ExtractColor> = emptyList(),
        val imageBitmap: Bitmap? = null
    ) : ImageState()
}