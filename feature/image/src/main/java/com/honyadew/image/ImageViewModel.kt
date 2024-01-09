package com.honyadew.image

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewModelScope
import com.honyadew.base.BaseViewModel
import com.honyadew.image.contract.ImageEffect
import com.honyadew.image.contract.ImageEvent
import com.honyadew.image.contract.ImageState
import com.honyadew.domain.usecase.SaveColorSchemeUseCase
import com.honyadew.image.R
import com.honyadew.extencion.addColor
import com.honyadew.extencion.removeColor
import com.honyadew.extencion.string
import com.honyadew.model.ColorInfo
import kotlinx.coroutines.launch

class ImageViewModel(
    private val saveColorScheme: SaveColorSchemeUseCase
) : BaseViewModel<ImageEvent, ImageState, ImageEffect>(initialState = ImageState.Loading) {
    init {
        viewState = ImageState.Show(
            selectedColor = Color.Black,
            //if in future i add saving last bitmap to a data, it's should be a changed.
            extractedColors = listOf(
                ColorInfo(value = Color(0xFF907828).string(), name = "Vibrant"),
                ColorInfo(value = Color(0xFF706008).string(), name = "Dark vibrant"),
                ColorInfo(value = Color(0xC7FFFFFF).string(), name = "On dark vibrant"),
                ColorInfo(value = Color(0xFF504808).string(), name = "Domain vibrant"),
                ColorInfo(value = Color(0xFFB0A0A0).string(), name = "Muted swatch"),
                ColorInfo(value = Color(0xFFC8B8B8).string(), name = "Light muted"),
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