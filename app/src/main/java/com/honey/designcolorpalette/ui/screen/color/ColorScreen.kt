package com.honey.designcolorpalette.ui.screen.color

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberCoroutineScope
import com.honey.designcolorpalette.ui.screen.color.contract.ColorEffect
import com.honey.designcolorpalette.ui.screen.color.contract.ColorEvent
import com.honey.designcolorpalette.ui.screen.color.contract.ColorState
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@Composable
fun ColorScreen(
    state: State<ColorState>,
    effect: SharedFlow<ColorEffect?>,
    onEventSend: (event: ColorEvent) -> Unit
){
    val coroutine = rememberCoroutineScope()
    when(val state = state.value){
        is ColorState.Loading -> {}
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