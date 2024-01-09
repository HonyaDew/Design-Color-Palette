package com.honyadew.designsystem.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.honyadew.GlobalSignals
import com.honyadew.designsystem.theme.colorSelect
import com.honyadew.extencion.color
import com.honyadew.extencion.toHexString

@Composable
fun DcpSlider(
    value: Float,
    onValueChange: (newValue: Float) -> Unit,
    modifier: Modifier = Modifier,
    steps: Int = 100,
    color: Color = colorSelect(inverse = true),
    leadingName: String = "",
    fullName: String = ""
) {
    val name = fullName.ifEmpty { "Value" }

    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        if (leadingName.isNotEmpty()) Text(
            text = leadingName,
            modifier.padding(start = 12.dp).clickable {
                GlobalSignals.snackbarHostState.tryEmit("$name: " + (steps*value).toString().split(".")[0])
            },
            fontWeight = FontWeight.SemiBold
        )
        Slider(
            modifier = Modifier.padding(horizontal = 8.dp),
            value = value,
            onValueChange = {
                GlobalSignals.snackbarHostState.tryEmit("$name: " + (steps*it).toString().split(".")[0])

                onValueChange.invoke(it)
            },
            valueRange = 0f..1f,
            steps = steps - 1,
            colors = SliderDefaults.colors(
                thumbColor = color,
                activeTrackColor = color,
                activeTickColor = color,
                inactiveTrackColor = colorSelect(saturation = 70),
                inactiveTickColor = colorSelect(saturation = 70)
            )
        )
    }
}