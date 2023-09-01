package com.honyadew.image

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewModelScope
import com.honyadew.base.BaseViewModel
import com.honyadew.image.contract.ImageEffect
import com.honyadew.image.contract.ImageEvent
import com.honyadew.image.contract.ImageState
import com.honey.domain.usecase.SaveColorSchemeUseCase
import com.honey.image.R
import com.honyadew.extencion.addColor
import com.honyadew.extencion.removeColor
import com.honyadew.extencion.string
import com.honyadew.model.ExtractColor
import kotlinx.coroutines.launch

class ImageViewModel(
    private val saveColorScheme: SaveColorSchemeUseCase
) : BaseViewModel<ImageEvent, ImageState, ImageEffect>(initialState = ImageState.Loading) {
    init {
        viewState = ImageState.Show(
            selectedColor = Color.Black,
            //if in future i add saving last bitmap to a data, it's should be a changed.
            extractedColors = listOf(
                ExtractColor(R.string.vibrant, color = Color(0xFF907828).string()),
                ExtractColor(R.string.dark_vibrant, color = Color(0xFF706008).string()),
                ExtractColor(R.string.on_dark_vibrant, color = Color(0xC7FFFFFF).string()),
                ExtractColor(R.string.domain_vibrant, color = Color(0xFF504808).string()),
                ExtractColor(R.string.muted_swatch, color = Color(0xFFB0A0A0).string()),
                ExtractColor(R.string.light_muted, color = Color(0xFFC8B8B8).string()),
            )
        )
    }
    override fun obtainEvent(event: ImageEvent) {
        when(val state = viewState){
            is ImageState.Loading -> {reduce(event, state)}
            is ImageState.Show -> {reduce(event, state)}
        }
    }

    private fun reduce (event: ImageEvent, state: ImageState.Loading){}
    private fun reduce (event: ImageEvent, state: ImageState.Show){
        when(event){
            is ImageEvent.SelectColor -> {
                viewState = state.copy(selectedColor = event.color)
            }
            is ImageEvent.RemoveFromToSave -> {
                viewState = state.copy(colorsToSave = state.colorsToSave.removeColor(event.color))
            }
            is ImageEvent.MoveToSave -> {
                viewState = state.copy(colorsToSave = state.colorsToSave.addColor(event.color))
            }
            is ImageEvent.SaveColorScheme -> {
                viewModelScope.launch {
                    saveColorScheme.invoke(event.colorScheme)
                    viewState = state.copy(colorsToSave = emptyList())
                }
            }
            is ImageEvent.SetExtractedColors -> {
                viewState = state.copy(extractedColors = event.extractedColors)
            }
            is ImageEvent.SetBitmap -> {
                viewState = state.copy(imageBitmap = event.bitmap)
            }
        }
    }
}