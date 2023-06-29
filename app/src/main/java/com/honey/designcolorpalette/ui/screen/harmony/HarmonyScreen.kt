package com.honey.designcolorpalette.ui.screen.harmony

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberCoroutineScope
import com.honey.designcolorpalette.ui.screen.harmony.contract.HarmonyEffect
import com.honey.designcolorpalette.ui.screen.harmony.contract.HarmonyEvent
import com.honey.designcolorpalette.ui.screen.harmony.contract.HarmonyState
import com.honey.designcolorpalette.ui.screen.harmony.view.HarmonyViewLoading
import com.honey.domain.model.ColorInfo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

@Composable
fun HarmonyScreen(
    state: State<HarmonyState>,
    effect: SharedFlow<HarmonyEffect?>,
    onEventSend: (event: HarmonyEvent) -> Unit,
    onColorClick: (color: ColorInfo) -> Unit,
    coroutine : CoroutineScope = rememberCoroutineScope()
) {
    when(val state = state.value){
        is HarmonyState.Loading -> {
            HarmonyViewLoading(state = state)
        }
        is HarmonyState.Show -> {

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