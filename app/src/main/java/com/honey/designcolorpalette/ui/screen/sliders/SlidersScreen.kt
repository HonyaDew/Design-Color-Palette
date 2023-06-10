package com.honey.designcolorpalette.ui.screen.sliders

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberCoroutineScope
import com.honey.domain.model.ColorInfo
import com.honey.designcolorpalette.ui.screen.sliders.contract.SlidersEffect
import com.honey.designcolorpalette.ui.screen.sliders.contract.SlidersEvent
import com.honey.designcolorpalette.ui.screen.sliders.contract.SlidersState
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
        is SlidersState.Loading -> {}

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