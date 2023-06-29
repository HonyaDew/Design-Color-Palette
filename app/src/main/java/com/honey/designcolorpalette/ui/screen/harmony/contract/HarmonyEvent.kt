package com.honey.designcolorpalette.ui.screen.harmony.contract

import androidx.compose.ui.graphics.Color
import com.honey.designcolorpalette.base.ViewEvent
import com.honey.domain.model.CustomColorScheme

sealed class HarmonyEvent : ViewEvent {
    data class ChangeHarmonyValue(
        val newValue: Color,
        val harmonyMode: HarmonyMode
    ) : HarmonyEvent()
    object SwapCopyMode : HarmonyEvent()
    data class SaveColorScheme(val colorScheme: CustomColorScheme) : HarmonyEvent()
}