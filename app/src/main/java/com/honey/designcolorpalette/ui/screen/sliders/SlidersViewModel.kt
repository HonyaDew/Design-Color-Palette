package com.honey.designcolorpalette.ui.screen.sliders

import com.honey.designcolorpalette.base.BaseViewModel
import com.honey.designcolorpalette.ui.screen.sliders.contract.SlidersEffect
import com.honey.designcolorpalette.ui.screen.sliders.contract.SlidersEvent
import com.honey.designcolorpalette.ui.screen.sliders.contract.SlidersState
import com.honey.designcolorpalette.ui.screen.sliders.contract.SlidersType

class SlidersViewModel : BaseViewModel<SlidersEvent, SlidersState, SlidersEffect>(initialState = SlidersState.Loading){

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
            is SlidersEvent.SelectRGB -> {viewState =state.copy(type = SlidersType.RGB)}
            is SlidersEvent.SelectHSV -> {viewState =state.copy(type = SlidersType.HSV)}
        }
    }

}
