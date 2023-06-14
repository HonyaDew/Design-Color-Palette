package com.honey.designcolorpalette.ui.screen.dialog.view

import android.icu.util.UniversalTimeScale
import android.text.Editable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.honey.designcolorpalette.R
import com.honey.designcolorpalette.ui.theme.colorSelect
import com.honey.domain.model.ColorOfMaterial
import com.honey.domain.model.EditableSettings
import com.honey.domain.model.Palette

@Composable
fun SettingsView(
    settings: EditableSettings,
    onChangePalette: (palette: Palette) -> Unit
) {
    SettingsSectionTitle(text = stringResource(id = R.string.palette))
    Column(modifier = Modifier.selectableGroup()) {
        SettingsRadioButton(
            text = stringResource(id = R.string.material),
            selected = settings.palette is Palette.Material,
            onClick = {onChangePalette.invoke(Palette.Material(subPalette = ColorOfMaterial.RED))}
        )
        SettingsRadioButton(
            text = stringResource(id = R.string.flatUI),
            selected = settings.palette is Palette.FlatUI,
            onClick = {onChangePalette.invoke(Palette.FlatUI)}
        )
        SettingsRadioButton(
            text = stringResource(id = R.string.social),
            selected = settings.palette is Palette.Social,
            onClick = {onChangePalette.invoke(Palette.Social)}
        )
        SettingsRadioButton(
            text = stringResource(id = R.string.metro),
            selected = settings.palette is Palette.Metro,
            onClick = {onChangePalette.invoke(Palette.Metro)}
        )
        SettingsRadioButton(
            text = stringResource(id = R.string.html),
            selected = settings.palette is Palette.HTML,
            onClick = {onChangePalette.invoke(Palette.HTML)}
        )

    }
}

@Composable
private fun SettingsRadioButton(
    text: String,
    selected: Boolean,
    onClick: () -> Unit
){
    Row(
        Modifier
            .fillMaxWidth()
            .selectable(
                selected = selected,
                role = Role.RadioButton,
                onClick = onClick,
            )
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        RadioButton(
            selected = selected,
            onClick = null,
            colors = RadioButtonDefaults.colors(selectedColor = colorSelect(70, inverse = true))
        )
        Spacer(Modifier.width(8.dp))
        Text(text)
    }
}

@Composable
private fun SettingsSectionTitle(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleMedium,
        modifier = Modifier.padding(top = 16.dp, bottom = 8.dp),
    )
}