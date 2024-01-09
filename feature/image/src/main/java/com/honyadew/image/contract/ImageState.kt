package com.honyadew.image.contract

import android.graphics.Bitmap
import androidx.compose.ui.graphics.Color
import com.honyadew.base.ViewState
import com.honyadew.model.ColorInfo

sealed class ImageState : com.honyadew.base.ViewState {
    object Loading : ImageState()
    data class Show(
        val selectedColor: Color,
        val colorsToSave: List<ColorInfo> = emptyList(),
        val extractedColors: List<ColorInfo> = emptyList(),
        val imageBitmap: Bitmap? = null
    ) : ImageState()
}