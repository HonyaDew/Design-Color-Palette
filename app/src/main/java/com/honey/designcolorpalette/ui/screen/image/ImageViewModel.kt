package com.honey.designcolorpalette.ui.screen.image

import android.app.Activity
import android.content.Context
import android.graphics.BitmapFactory
import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewModelScope
import com.honey.designcolorpalette.R
import com.honey.designcolorpalette.base.BaseViewModel
import com.honey.designcolorpalette.extencion.string
import com.honey.designcolorpalette.ui.screen.image.contract.ImageEffect
import com.honey.designcolorpalette.ui.screen.image.contract.ImageEvent
import com.honey.designcolorpalette.ui.screen.image.contract.ImageState
import com.honey.domain.model.ExtractColor
import com.honey.domain.usecase.AddColorToListUseCase
import com.honey.domain.usecase.RemoveColorFromListUseCase
import com.honey.domain.usecase.SaveColorSchemeUseCase
import kotlinx.coroutines.launch
import kotlin.coroutines.coroutineContext

class ImageViewModel(
    private val saveColorScheme: SaveColorSchemeUseCase,
    private val addColorToList: AddColorToListUseCase,
    private val removeColorFromList: RemoveColorFromListUseCase
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
                viewState = state.copy(colorsToSave = removeColorFromList.invoke(state.colorsToSave, event.color))
            }
            is ImageEvent.MoveToSave -> {
                viewState = state.copy(colorsToSave = addColorToList.invoke(state.colorsToSave, event.color))
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