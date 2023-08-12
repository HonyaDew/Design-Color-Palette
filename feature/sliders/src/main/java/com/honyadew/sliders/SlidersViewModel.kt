package com.honyadew.sliders

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.honyadew.base.BaseViewModel
import com.honyadew.sliders.contract.SlidersEffect
import com.honyadew.sliders.contract.SlidersEvent
import com.honyadew.sliders.contract.SlidersState
import com.honey.domain.usecase.SaveColorSchemeUseCase
import com.honyadew.extencion.addColor
import com.honyadew.extencion.removeColor
import com.honyadew.sliders.model.SlidersType
import kotlinx.coroutines.launch

class SlidersViewModel(
    private val saveColorScheme: SaveColorSchemeUseCase
) : BaseViewModel<SlidersEvent, SlidersState, SlidersEffect>(initialState = SlidersState.Loading){

    init {
        viewState = SlidersState.Show(type = SlidersType.RGB)
    }

    override fun obtainEvent(event: SlidersEvent) {
        when(val state = viewState){
            is SlidersState.Loading -> {reduce(event, state)}
            is SlidersState.Show -> {reduce(event, state)}
        }
    }

    private fun reduce(event: SlidersEvent, state: SlidersState.Loading){

    }

    private fun reduce(event: SlidersEvent, state: SlidersState.Show){
        when(event){
            is SlidersEvent.SetFirstSliderValue -> {viewState = state.copy(sliderOne = event.newValue)}
            is SlidersEvent.SetSecondSliderValue -> {viewState = state.copy(sliderTwo = event.newValue)}
            is SlidersEvent.SetThirdSliderValue -> {viewState = state.copy(sliderThree = event.newValue)}
            is SlidersEvent.SetAlphaSliderValue -> {viewState = state.copy(sliderAlpha = event.newValue)}
            SlidersEvent.SelectRGB -> {viewState =state.copy(type = SlidersType.RGB)}
            SlidersEvent.SelectHSV -> {viewState =state.copy(type = SlidersType.HSV)}
            is SlidersEvent.AddColorToSaveList -> {
                viewState = state.copy(colorsToSave = state.colorsToSave.addColor(event.color))
            }
            is SlidersEvent.RemoveColorFromToSaveList -> {
                viewState = state.copy(colorsToSave = state.colorsToSave.removeColor(event.color))
            }
            is SlidersEvent.SaveColorScheme -> {
                viewModelScope.launch {
                    saveColorScheme.invoke(event.colorScheme)
                    viewState = state.copy(colorsToSave = emptyList())
                }
            }
        }
    }
}
//fun extractColorsFromBitmap(bitmap: Bitmap): Map<String, String> {
//    return mapOf(
//        "vibrant" to parseColorSwatch(
//            color = Palette.from(bitmap).generate().vibrantSwatch
//        ),
//        "darkVibrant" to parseColorSwatch(
//            color = Palette.from(bitmap).generate().darkVibrantSwatch
//        ),
//        "onDarkVibrant" to parseBodyColor(
//            color = Palette.from(bitmap).generate().darkVibrantSwatch?.bodyTextColor
//        ),
//        "lightVibrant" to parseColorSwatch(
//            color = Palette.from(bitmap).generate().lightVibrantSwatch
//        ),
//        "domainSwatch" to parseColorSwatch(
//            color = Palette.from(bitmap).generate().dominantSwatch
//        ),
//        "mutedSwatch" to parseColorSwatch(
//            color = Palette.from(bitmap).generate().mutedSwatch
//        ),
//        "lightMuted" to parseColorSwatch(
//            color = Palette.from(bitmap).generate().lightMutedSwatch
//        ),
//        "darkMuted" to parseColorSwatch(
//            color = Palette.from(bitmap).generate().darkMutedSwatch
//        ),
//    )
//}