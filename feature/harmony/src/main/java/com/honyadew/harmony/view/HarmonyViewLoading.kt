package com.honyadew.harmony.view

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import com.honyadew.harmony.contract.HarmonyState
import com.honyadew.designsystem.theme.colorSelect

@Composable
fun HarmonyViewLoading(
    state: HarmonyState.Loading
) {
    CircularProgressIndicator(color = colorSelect(saturation = 70))
}