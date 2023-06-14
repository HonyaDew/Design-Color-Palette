package com.honey.designcolorpalette.ui.screen.palette

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberCoroutineScope
import com.honey.designcolorpalette.showSettingsState
import com.honey.designcolorpalette.ui.main.LocalActiveColor
import com.honey.domain.model.ColorInfo
import com.honey.designcolorpalette.ui.screen.palette.contract.PaletteEffect
import com.honey.designcolorpalette.ui.screen.palette.contract.PaletteEvent
import com.honey.designcolorpalette.ui.screen.palette.contract.PaletteState
import com.honey.designcolorpalette.ui.screen.palette.view.PaletteViewLoading
import com.honey.designcolorpalette.ui.screen.palette.view.PaletteViewMain
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@Composable
fun PaletteScreen(
    state: State<PaletteState>,
    effect: SharedFlow<PaletteEffect?>,
    onEventSend: (event: PaletteEvent) -> Unit,
    onColorClick: (color: ColorInfo) -> Unit
) {
    val coroutine = rememberCoroutineScope()

    when (val state = state.value) {
        is PaletteState.Loading -> {
            PaletteViewLoading(state = state)
        }

        is PaletteState.Show -> {
            PaletteViewMain(
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
            showSettingsState.collect(){show->
                onEventSend.invoke(PaletteEvent.UpdatePalette)
            }
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
}
