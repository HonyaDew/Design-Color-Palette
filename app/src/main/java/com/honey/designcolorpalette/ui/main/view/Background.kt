package com.honey.designcolorpalette.ui.main.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.honey.designcolorpalette.ui.theme.Purple40
import com.honey.designcolorpalette.ui.theme.PurpleGrey40

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DcpBackground(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
){
    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = modifier
            .fillMaxSize()
    ) {
        content.invoke()
    }
}


