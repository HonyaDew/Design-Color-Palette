package com.honyadew.palette

import com.honyadew.base.BaseViewModel
import com.honyadew.palette.contract.PaletteEffect
import com.honyadew.palette.contract.PaletteEvent
import com.honyadew.palette.contract.PaletteState
import com.honey.domain.usecase.GetColorByPaletteUseCase
import com.honey.domain.usecase.GetSettingsUseCase
import com.honey.domain.usecase.PutSettingsUseCase
import com.honyadew.model.Palette

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

