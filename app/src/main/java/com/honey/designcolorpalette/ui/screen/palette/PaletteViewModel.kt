package com.honey.designcolorpalette.ui.screen.palette

import com.honey.designcolorpalette.base.BaseViewModel
import com.honey.domain.model.Palette
import com.honey.designcolorpalette.ui.screen.palette.contract.PaletteEffect
import com.honey.designcolorpalette.ui.screen.palette.contract.PaletteEvent
import com.honey.designcolorpalette.ui.screen.palette.contract.PaletteState
import com.honey.domain.usecase.GetColorByPaletteUseCase
import com.honey.domain.usecase.GetSettingsUseCase
import com.honey.domain.usecase.PutSettingsUseCase

class PaletteViewModel(
    private val getSettings : GetSettingsUseCase,
    private val putSettings : PutSettingsUseCase,
    private val getColorsByPalette: GetColorByPaletteUseCase
) : BaseViewModel<PaletteEvent, PaletteState, PaletteEffect>(initialState =  PaletteState.Loading) {


    override fun obtainEvent(event: PaletteEvent) {
        when(val state = viewState){
            is PaletteState.Loading -> {reduce(event,state)}
            is PaletteState.Show -> {reduce(event,state)}
        }
    }

    private fun reduce(event: PaletteEvent, state: PaletteState.Loading){
        when(event){
            is PaletteEvent.UpdatePalette -> {
                performLoadPalette(getSettings.invoke().palette)
            }
            else -> {}
        }
    }

    private fun reduce(event: PaletteEvent, state: PaletteState.Show){
        when(event){
            is PaletteEvent.SelectSubPalette -> {
                putSettings.invoke(getSettings.invoke().copy(palette = event.palette))
                performLoadPalette(event.palette)
            }
            is PaletteEvent.UpdatePalette -> {
                performLoadPalette(getSettings.invoke().palette)
            }
            else -> {}
        }
    }

    private fun performLoadPalette(palette: Palette){
        val colorsToShow = getColorsByPalette.invoke(palette)

        viewState = PaletteState.Show(colorsToShow, palette)
    }
}

