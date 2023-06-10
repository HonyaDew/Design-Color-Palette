package com.honey.designcolorpalette.ui.screen.palette

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberCoroutineScope
import com.honey.domain.model.ColorInfo
import com.honey.designcolorpalette.ui.screen.palette.contract.PaletteEffect
import com.honey.designcolorpalette.ui.screen.palette.contract.PaletteEvent
import com.honey.designcolorpalette.ui.screen.palette.contract.PaletteState
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

@Composable
fun PaletteScreen(
    state: State<PaletteState>,
    effect: SharedFlow<PaletteEffect?>,
    onEventSend: (event: PaletteEvent) -> Unit,
    onColorClick: (color: ColorInfo) -> Unit
) {
    val coroutine = rememberCoroutineScope()

    when(val state = state.value){
        is PaletteState.Loading -> {}
        else -> {}
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
