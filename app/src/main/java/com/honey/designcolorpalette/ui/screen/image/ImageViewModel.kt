package com.honey.designcolorpalette.ui.screen.image

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewModelScope
import com.honey.designcolorpalette.base.BaseViewModel
import com.honey.designcolorpalette.ui.screen.image.contract.ImageEffect
import com.honey.designcolorpalette.ui.screen.image.contract.ImageEvent
import com.honey.designcolorpalette.ui.screen.image.contract.ImageState
import com.honey.domain.usecase.AddColorToListUseCase
import com.honey.domain.usecase.RemoveColorFromListUseCase
import com.honey.domain.usecase.SaveColorSchemeUseCase
import kotlinx.coroutines.launch

class ImageViewModel(
    private val saveColorScheme: SaveColorSchemeUseCase,
    private val addColorToList: AddColorToListUseCase,
    private val removeColorFromList: RemoveColorFromListUseCase
) : BaseViewModel<ImageEvent, ImageState, ImageEffect>(initialState = ImageState.Loading) {
    init {
        viewState = ImageState.Show(selectedColor = Color.Black)
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
        }
    }
}