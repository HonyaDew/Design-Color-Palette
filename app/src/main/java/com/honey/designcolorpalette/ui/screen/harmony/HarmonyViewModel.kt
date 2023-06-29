package com.honey.designcolorpalette.ui.screen.harmony

import com.honey.designcolorpalette.base.BaseViewModel
import com.honey.designcolorpalette.ui.screen.harmony.contract.HarmonyEffect
import com.honey.designcolorpalette.ui.screen.harmony.contract.HarmonyEvent
import com.honey.designcolorpalette.ui.screen.harmony.contract.HarmonyState

class HarmonyViewModel : BaseViewModel<HarmonyEvent, HarmonyState, HarmonyEffect>(
    initialState = HarmonyState.Loading
) {
    override fun obtainEvent(event: HarmonyEvent) {
        TODO("Not yet implemented")
    }
}