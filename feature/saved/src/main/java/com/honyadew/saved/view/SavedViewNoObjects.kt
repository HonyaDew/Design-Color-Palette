package com.honyadew.saved.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.honyadew.designsystem.theme.colorSelect
import com.honyadew.saved.contract.SavedState

@Composable
fun SavedViewNoObjects(
    state: SavedState.NoObjects
) {
    Box(modifier = Modifier.fillMaxSize().padding(16.dp), contentAlignment = Alignment.Center){
        Text("You don't have saved color schemes", color = colorSelect(saturation = 90, inverse = true))
    }
}