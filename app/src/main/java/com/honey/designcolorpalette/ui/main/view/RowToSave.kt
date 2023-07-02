package com.honey.designcolorpalette.ui.main.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.honey.designcolorpalette.R
import com.honey.designcolorpalette.extencion.color
import com.honey.designcolorpalette.ui.screen.sliders.contract.SlidersState
import com.honey.designcolorpalette.ui.theme.colorSelect
import com.honey.domain.model.ColorInfo
import com.honey.domain.model.CustomColorScheme

@Composable
fun RowToSave(
    colorsToSave: List<ColorInfo>,
    onSaveColorScheme: (colorScheme: CustomColorScheme) -> Unit,
    onRemoveFromToSaveList : (color : ColorInfo) -> Unit,
    modifier: Modifier = Modifier,
    showNameField: MutableState<Boolean> = remember{ mutableStateOf(false) },
    textNameField: MutableState<String> = remember{ mutableStateOf("") }
) {
    Column(modifier = modifier) {
        Row(modifier = Modifier.padding(bottom = 4.dp), verticalAlignment = Alignment.CenterVertically) {
            OutlinedCard(
                modifier = Modifier.fillMaxWidth(0.8f),
                shape = RoundedCornerShape(16.dp),
                border = BorderStroke(width = 2.dp, color = colorSelect()),
                elevation = CardDefaults.cardElevation(2.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                ) {
                    colorsToSave.forEach { colorInfo ->
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .background(colorInfo.value.color())
                                .height(48.dp)
                                .clickable { onRemoveFromToSaveList.invoke(colorInfo) }
                        )
                    }
                }
            }
            val arrowIconResId = if (showNameField.value) R.drawable.ic_arrow_to_bottom else R.drawable.ic_arrow_to_end
            IconButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    showNameField.value = !showNameField.value
                }
            ) {
                Icon(painter = painterResource(id = arrowIconResId), contentDescription = "Arrow")
            }
        }
        if (showNameField.value){
            Row(modifier = Modifier.padding(bottom = 4.dp), verticalAlignment = Alignment.CenterVertically) {
                OutlinedTextField(
                    value = textNameField.value,
                    onValueChange = {newValue ->
                        if (newValue.length < 21) textNameField.value = newValue
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.8f),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = colorSelect(saturation = 70),
                        unfocusedBorderColor = colorSelect(saturation = 90),
                        focusedLabelColor = colorSelect(saturation = 70),
                        cursorColor = colorSelect(saturation = 90, inverse = true)
                    ),
                    singleLine = true,
                    label = {
                        Text(text = stringResource(id = R.string.name_color_scheme))
                    }
                )
                IconButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        onSaveColorScheme.invoke(
                            CustomColorScheme(
                                colors = colorsToSave,
                                name = textNameField.value,
                            )
                        )
                    }
                ) {
                    Icon(painter = painterResource(id = R.drawable.ic_save_24), contentDescription = "Save")
                }
            }
        }
    }
}