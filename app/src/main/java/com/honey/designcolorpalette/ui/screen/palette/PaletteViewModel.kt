package com.honey.designcolorpalette.ui.screen.palette

import androidx.compose.ui.graphics.Color
import com.honey.designcolorpalette.base.BaseViewModel
import com.honey.designcolorpalette.extencion.string
import com.honey.domain.model.ColorInfo
import com.honey.domain.model.ColorOfMaterial
import com.honey.domain.model.Palette
import com.honey.designcolorpalette.ui.screen.palette.contract.PaletteEffect
import com.honey.designcolorpalette.ui.screen.palette.contract.PaletteEvent
import com.honey.designcolorpalette.ui.screen.palette.contract.PaletteState
import com.honey.domain.usecase.GetSettingsUseCase

class PaletteViewModel(
    private val getSettings : GetSettingsUseCase
) : BaseViewModel<PaletteEvent, PaletteState, PaletteEffect>(initialState =  PaletteState.Loading) {

    init {
        //TODO Load previous
        performLoadPalette(palette = Palette.Material(subPalette = ColorOfMaterial.RED))
    }

    override fun obtainEvent(event: PaletteEvent) {
        when(val state = viewState){
            is PaletteState.Loading -> {reduce(event,state)}
            is PaletteState.Show -> {reduce(event,state)}
        }
    }

    private fun reduce(event: PaletteEvent, state: PaletteState.Loading){

    }

    private fun reduce(event: PaletteEvent, state: PaletteState.Show){

    }

    private fun performLoadPalette(palette: Palette){
        val colorsToShow = getColor(palette)

        viewState = PaletteState.Show(colorsToShow, palette)
    }
}

//TODO change to use case
fun getColor(palette: Palette): List<ColorInfo>{
    val materialRedList = listOf<ColorInfo>(
        ColorInfo(
            value = Color(0xFFFFEBEE).string(),
            name = "50",
            palette = Palette.Material(subPalette = ColorOfMaterial.RED)
        ),
        ColorInfo(
            value = Color(0xFFFFCDD2).string(),
            name = "100",
            palette = Palette.Material(subPalette = ColorOfMaterial.RED)
        ),
        ColorInfo(
            value = Color(0xFFEF9A9A).string(),
            name = "200",
            palette = Palette.Material(subPalette = ColorOfMaterial.RED)
        ),
        ColorInfo(
            value = Color(0xFFE57373).string(),
            name = "300",
            palette = Palette.Material(subPalette = ColorOfMaterial.RED)
        ),
        ColorInfo(
            value = Color(0xFFEF5350).string(),
            name = "400",
            palette = Palette.Material(subPalette = ColorOfMaterial.RED)
        )
    )
    val materialPinkList = listOf<ColorInfo>(
        ColorInfo(
            value = Color(0xFFFCE4EC).string(),
            name = "50",
            palette = Palette.Material(subPalette = ColorOfMaterial.PINK)
        ),
        ColorInfo(
            value = Color(0xFFF8BBD0).string(),
            name = "100",
            palette = Palette.Material(subPalette = ColorOfMaterial.PINK)
        ),
        ColorInfo(
            value = Color(0xFFF48FB1).string(),
            name = "200",
            palette = Palette.Material(subPalette = ColorOfMaterial.PINK)
        ),
        ColorInfo(
            value = Color(0xFFF06292).string(),
            name = "300",
            palette = Palette.Material(subPalette = ColorOfMaterial.PINK)
        ),
        ColorInfo(
            value = Color(0xFFEC407A).string(),
            name = "400",
            palette = Palette.Material(subPalette = ColorOfMaterial.PINK)
        ),
    )
    return when(palette){
        Palette.Material(subPalette = ColorOfMaterial.RED) -> {
            materialRedList
        }
        Palette.Material(subPalette = ColorOfMaterial.PINK) -> {
            materialPinkList
        }
        else -> {
            emptyList<ColorInfo>()
        }
    }
}