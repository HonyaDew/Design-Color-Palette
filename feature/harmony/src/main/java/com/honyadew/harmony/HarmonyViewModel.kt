package com.honyadew.harmony

import androidx.lifecycle.viewModelScope
import com.honyadew.harmony.contract.HarmonyEffect
import com.honyadew.harmony.contract.HarmonyEvent
import com.honyadew.harmony.contract.HarmonyMode
import com.honyadew.harmony.contract.HarmonyState
import com.honyadew.domain.usecase.SaveColorSchemeUseCase
import kotlinx.coroutines.launch

class HarmonyViewModel(
    private val saveColorScheme: SaveColorSchemeUseCase
) : com.honyadew.base.BaseViewModel<HarmonyEvent, HarmonyState, HarmonyEffect>(
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