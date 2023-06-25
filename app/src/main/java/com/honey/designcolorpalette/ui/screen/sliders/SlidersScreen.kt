package com.honey.designcolorpalette.ui.screen.sliders

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberCoroutineScope
import com.honey.domain.model.ColorInfo
import com.honey.designcolorpalette.ui.screen.sliders.contract.SlidersEffect
import com.honey.designcolorpalette.ui.screen.sliders.contract.SlidersEvent
import com.honey.designcolorpalette.ui.screen.sliders.contract.SlidersState
import com.honey.designcolorpalette.ui.screen.sliders.contract.SlidersType
import com.honey.designcolorpalette.ui.screen.sliders.view.SlidersViewLoading
import com.honey.designcolorpalette.ui.screen.sliders.view.SlidersViewShow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

@Composable
fun SlidersScreen(
    state: State<SlidersState>,
    effect : SharedFlow<SlidersEffect?>,
    onEventSend: (event: SlidersEvent) -> Unit,
    onColorClick: (color: ColorInfo) -> Unit
){
    val coroutine = rememberCoroutineScope()
    when(val state = state.value){
        is SlidersState.Loading -> {
            SlidersViewLoading(state = state)
        }
        is SlidersState.Show -> {
            SlidersViewShow(
                state = state,
                onFirstSliderChange = {newValue -> onEventSend.invoke(SlidersEvent.SetFirstSliderValue(newValue)) },
                onSecondSliderChange = {newValue -> onEventSend.invoke(SlidersEvent.SetSecondSliderValue(newValue)) },
                onThirdSliderChange = {newValue -> onEventSend.invoke(SlidersEvent.SetThirdSliderValue(newValue)) },
                onAlphaSliderChange = {newValue -> onEventSend.invoke(SlidersEvent.SetAlphaSliderValue(newValue)) },
                onChangeSlidersType = {newType ->
                    onEventSend.invoke(
                        when(newType){
                            SlidersType.RGB -> {SlidersEvent.SelectRGB}
                            SlidersType.HSV -> {SlidersEvent.SelectHSV}
                        }
                    )
                },
                onSaveColorScheme = {colorScheme -> onEventSend.invoke(SlidersEvent.SaveColorScheme(colorScheme)) },
                onAddToSaveList = {color -> onEventSend.invoke(SlidersEvent.AddColorToSaveList(color)) },
                onRemoveFromToSaveList = {color -> onEventSend.invoke(SlidersEvent.RemoveColorFromToSaveList(color)) }
            )
        }

    }

    SideEffect {
        coroutine.launch {
            effect.collect(){effect ->
                when(effect){

                    else -> {}
                }
            }
        }
    }
}