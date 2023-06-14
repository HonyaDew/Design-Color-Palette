package com.honey.designcolorpalette.ui.screen.palette

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.currentRecomposeScope
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewModelScope
import com.honey.designcolorpalette.FlatUIPalette
import com.honey.designcolorpalette.HTMLPalette
import com.honey.designcolorpalette.MaterialAmber
import com.honey.designcolorpalette.MaterialBlue
import com.honey.designcolorpalette.MaterialBlueGray
import com.honey.designcolorpalette.MaterialBrown
import com.honey.designcolorpalette.MaterialCyan
import com.honey.designcolorpalette.MaterialDeepOrange
import com.honey.designcolorpalette.MaterialDeepPurple
import com.honey.designcolorpalette.MaterialGreen
import com.honey.designcolorpalette.MaterialGrey
import com.honey.designcolorpalette.MaterialIndigo
import com.honey.designcolorpalette.MaterialLightBlue
import com.honey.designcolorpalette.MaterialLightGreen
import com.honey.designcolorpalette.MaterialLime
import com.honey.designcolorpalette.MaterialOrange
import com.honey.designcolorpalette.MaterialPink
import com.honey.designcolorpalette.MaterialPurple
import com.honey.designcolorpalette.MaterialRed
import com.honey.designcolorpalette.MaterialTeal
import com.honey.designcolorpalette.MaterialYellow
import com.honey.designcolorpalette.MetroPalette
import com.honey.designcolorpalette.SocialPalette
import com.honey.designcolorpalette.base.BaseViewModel
import com.honey.designcolorpalette.showSettingsState
import com.honey.designcolorpalette.extencion.string
import com.honey.domain.model.ColorInfo
import com.honey.domain.model.ColorOfMaterial
import com.honey.domain.model.Palette
import com.honey.designcolorpalette.ui.screen.palette.contract.PaletteEffect
import com.honey.designcolorpalette.ui.screen.palette.contract.PaletteEvent
import com.honey.designcolorpalette.ui.screen.palette.contract.PaletteState
import com.honey.domain.usecase.GetColorByPaletteUseCase
import com.honey.domain.usecase.GetSettingsUseCase
import com.honey.domain.usecase.PutSettingsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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
        val colorsToShow = getColor(palette)

        viewState = PaletteState.Show(colorsToShow, palette)
    }
}

//TODO change to use case
fun getColor(palette: Palette): List<ColorInfo>{

    return when(palette){
        Palette.Material(subPalette = ColorOfMaterial.RED) -> { MaterialRed }
        Palette.Material(subPalette = ColorOfMaterial.PINK) -> { MaterialPink }
        Palette.Material(subPalette = ColorOfMaterial.PURPLE) -> { MaterialPurple }
        Palette.Material(subPalette = ColorOfMaterial.DEEP_PURPLE) -> { MaterialDeepPurple }
        Palette.Material(subPalette = ColorOfMaterial.INDIGO) -> { MaterialIndigo }
        Palette.Material(subPalette = ColorOfMaterial.BLUE) -> { MaterialBlue }
        Palette.Material(subPalette = ColorOfMaterial.LIGHT_BLUE) -> { MaterialLightBlue }
        Palette.Material(subPalette = ColorOfMaterial.CYAN) -> { MaterialCyan }
        Palette.Material(subPalette = ColorOfMaterial.TEAL) -> { MaterialTeal }
        Palette.Material(subPalette = ColorOfMaterial.GREEN) -> { MaterialGreen }
        Palette.Material(subPalette = ColorOfMaterial.LIGHT_GREEN) -> { MaterialLightGreen }
        Palette.Material(subPalette = ColorOfMaterial.LIME) -> { MaterialLime }
        Palette.Material(subPalette = ColorOfMaterial.YELLOW) -> { MaterialYellow }
        Palette.Material(subPalette = ColorOfMaterial.AMBER) -> { MaterialAmber }
        Palette.Material(subPalette = ColorOfMaterial.ORANGE) -> { MaterialOrange }
        Palette.Material(subPalette = ColorOfMaterial.DEEP_ORANGE) -> { MaterialDeepOrange }
        Palette.Material(subPalette = ColorOfMaterial.BROWN) -> { MaterialBrown }
        Palette.Material(subPalette = ColorOfMaterial.GREY) -> { MaterialGrey }
        Palette.Material(subPalette = ColorOfMaterial.BLUE_GRAY) -> { MaterialBlueGray }
        Palette.FlatUI -> { FlatUIPalette}
        Palette.Social -> { SocialPalette }
        Palette.Metro -> { MetroPalette }
        Palette.HTML -> { HTMLPalette}
        else -> {
            emptyList<ColorInfo>()
        }
    }
}