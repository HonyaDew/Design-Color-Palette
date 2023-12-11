package com.honyadew.palette

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberCoroutineScope
import com.honyadew.GlobalSignals
import com.honyadew.palette.contract.PaletteEffect
import com.honyadew.palette.contract.PaletteEvent
import com.honyadew.palette.contract.PaletteState
import com.honyadew.palette.view.PaletteViewLoading
import com.honyadew.palette.view.PaletteViewShow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

@Composable
fun PaletteScreen(
    state: State<PaletteState>,
    effect: SharedFlow<PaletteEffect?>,
    onEventSend: (event: PaletteEvent) -> Unit,
    onColorClick: (color: com.honyadew.model.ColorInfo) -> Unit,
    coroutine : CoroutineScope = rememberCoroutineScope()
) {

    when (val state = state.value) {
        is PaletteState.Loading -> {
            PaletteViewLoading(state = state)
        }

        is PaletteState.Show -> {
            PaletteViewShow(
                state = state,
                onColorClick = onColorClick,
                onSelectSubPalette = { palette ->
                    onEventSend.invoke(PaletteEvent.SelectSubPalette(palette))
                }
            )
        }
    }

    SideEffect {
        coroutine.launch {
            effect.collect() { effect ->
                when (effect) {

                    else -> {}
                }
            }
        }
    }

    //Perform reload palettes, when settings dialog is closing
    SideEffect {
        coroutine.launch {
            GlobalSignals.showSettingsState.collect(){ show->
                if (!show) onEventSend.invoke(PaletteEvent.UpdatePalette)
            }
        }
    }
}
