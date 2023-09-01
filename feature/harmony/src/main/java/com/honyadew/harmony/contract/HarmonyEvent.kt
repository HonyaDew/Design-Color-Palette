package com.honyadew.harmony.contract

import androidx.compose.ui.graphics.Color
import com.honyadew.model.CustomColorScheme

sealed class HarmonyEvent : com.honyadew.base.ViewEvent {
    data class ChangeHarmonyValue(
        val newValue: Color,
        val harmonyMode: HarmonyMode
    ) : HarmonyEvent()
    object SwapCopyMode : HarmonyEvent()
    data class SaveColorScheme(val colorScheme: CustomColorScheme) : HarmonyEvent()
}