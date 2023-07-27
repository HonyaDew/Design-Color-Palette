package com.honyadew.harmony.contract

import androidx.compose.ui.graphics.Color

sealed class HarmonyEvent : com.honyadew.base.ViewEvent {
    data class ChangeHarmonyValue(
        val newValue: Color,
        val harmonyMode: HarmonyMode
    ) : HarmonyEvent()
    object SwapCopyMode : HarmonyEvent()
    data class SaveColorScheme(val colorScheme: com.honyadew.model.CustomColorScheme) : HarmonyEvent()
}