package com.honey.designcolorpalette.ui.screen.harmony

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewModelScope
import com.honey.designcolorpalette.base.BaseViewModel
import com.honey.designcolorpalette.ui.screen.harmony.contract.HarmonyEffect
import com.honey.designcolorpalette.ui.screen.harmony.contract.HarmonyEvent
import com.honey.designcolorpalette.ui.screen.harmony.contract.HarmonyMode
import com.honey.designcolorpalette.ui.screen.harmony.contract.HarmonyState
import com.honey.domain.usecase.SaveColorSchemeUseCase
import kotlinx.coroutines.launch

class HarmonyViewModel(
    private val saveColorScheme: SaveColorSchemeUseCase
) : BaseViewModel<HarmonyEvent, HarmonyState, HarmonyEffect>(
    initialState = HarmonyState.Loading
) {
    init {
        viewState = HarmonyState.Show()
    }
    override fun obtainEvent(event: HarmonyEvent) {
        when(val state = viewState){
            is HarmonyState.Loading -> {reduce(event, state)}
            is HarmonyState.Show -> {reduce(event, state)}
        }
    }

    private fun reduce(event: HarmonyEvent, state: HarmonyState.Loading) {}
    private fun reduce(event: HarmonyEvent, state: HarmonyState.Show) {
        when(event){
            is HarmonyEvent.ChangeHarmonyValue -> {
                val arraylist = ArrayList(state.harmoniesValue)
                arraylist[HarmonyMode.values().indexOf(event.harmonyMode)] = event.newValue
                viewState = state.copy(harmoniesValue = arraylist.toList())
            }
            is HarmonyEvent.SaveColorScheme -> {
                viewModelScope.launch {
                    saveColorScheme.invoke(event.colorScheme)
                }
            }
            is HarmonyEvent.SwapCopyMode -> {
                viewState = state.copy(hexAsCopyMode = !state.hexAsCopyMode)
            }
        }
    }
}