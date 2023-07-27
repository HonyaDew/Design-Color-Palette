package com.honyadew.harmony

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberCoroutineScope
import com.honyadew.harmony.contract.HarmonyEffect
import com.honyadew.harmony.contract.HarmonyEvent
import com.honyadew.harmony.contract.HarmonyState
import com.honyadew.harmony.view.HarmonyViewLoading
import com.honyadew.harmony.view.HarmonyViewShow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HarmonyScreen(
    state: State<HarmonyState>,
    effect: SharedFlow<HarmonyEffect?>,
    onEventSend: (event: HarmonyEvent) -> Unit,
    onColorClick: (color: com.honyadew.model.ColorInfo) -> Unit,
    coroutine : CoroutineScope = rememberCoroutineScope()
) {
    when(val state = state.value){
        is HarmonyState.Loading -> {
            HarmonyViewLoading(state = state)
        }
        is HarmonyState.Show -> {
            HarmonyViewShow(
                state = state,
                onColorValueChanged = { newValue, harmony ->
                    onEventSend.invoke(
                        HarmonyEvent.ChangeHarmonyValue(newValue, harmony)
                    )
                },
                onSwapCopyMode = {onEventSend.invoke(HarmonyEvent.SwapCopyMode)},
                onSave = { colorScheme ->
                    onEventSend.invoke(
                        HarmonyEvent.SaveColorScheme(colorScheme)
                    )
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
}