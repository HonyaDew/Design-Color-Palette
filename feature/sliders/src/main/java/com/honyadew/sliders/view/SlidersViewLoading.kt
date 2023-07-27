package com.honyadew.sliders.view

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import com.honyadew.sliders.contract.SlidersState

@Composable
fun SlidersViewLoading(
    state: SlidersState.Loading
) {
    CircularProgressIndicator()
}