package com.honey.designcolorpalette.ui.screen.sliders

import android.graphics.Bitmap
import android.util.Log
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.viewModelScope
import com.honey.designcolorpalette.base.BaseViewModel
import com.honey.designcolorpalette.ui.screen.sliders.contract.SlidersEffect
import com.honey.designcolorpalette.ui.screen.sliders.contract.SlidersEvent
import com.honey.designcolorpalette.ui.screen.sliders.contract.SlidersState
import com.honey.designcolorpalette.ui.screen.sliders.contract.SlidersType
import com.honey.domain.usecase.AddColorToListUseCase
import com.honey.domain.usecase.RemoveColorFromListUseCase
import com.honey.domain.usecase.SaveColorSchemeUseCase
import kotlinx.coroutines.launch

class SlidersViewModel(
    private val saveColorScheme: SaveColorSchemeUseCase,
    private val addColorToList: AddColorToListUseCase,
    private val removeColorFromList: RemoveColorFromListUseCase
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
                Log.d("MyLog", "now is list -> ${state.colorsToSave}")
                viewState = state.copy(colorsToSave = addColorToList.invoke(state.colorsToSave, event.color))
                Log.d("MyLog", "aft is list -> ${state.colorsToSave}")

            }
            is SlidersEvent.RemoveColorFromToSaveList -> {
                viewState = state.copy(colorsToSave = removeColorFromList.invoke(state.colorsToSave, event.color))
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