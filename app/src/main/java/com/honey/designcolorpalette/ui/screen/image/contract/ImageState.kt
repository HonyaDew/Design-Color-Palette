package com.honey.designcolorpalette.ui.screen.image.contract

import android.graphics.Bitmap
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import com.honey.designcolorpalette.base.ViewState
import com.honey.domain.model.ColorInfo
import com.honey.domain.model.CustomColorScheme
import com.honey.domain.model.ExtractColor

sealed class ImageState : ViewState {
    object Loading : ImageState()
    data class Show(
        val selectedColor: Color,
        val colorsToSave : List<ColorInfo> = emptyList(),
        val extractedColors : List<ExtractColor> = emptyList(),
        val imageBitmap: Bitmap? = null
    ) : ImageState()
}