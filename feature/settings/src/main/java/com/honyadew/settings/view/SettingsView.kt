package com.honyadew.settings.view

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
import com.honyadew.designsystem.theme.colorSelect
import com.honyadew.model.ColorOfMaterial
import com.honyadew.model.Palette
import com.honyadew.settings.R
import com.honyadew.model.ThemeConfig

@Composable
fun SettingsView(
    settings: com.honyadew.model.EditableSettings,
    onChangePalette: (palette: com.honyadew.model.Palette) -> Unit,
    onChangeThemeConfig: (themeConfig: ThemeConfig) -> Unit
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
    SettingsSectionTitle(text = stringResource(id = R.string.theme_preference))
    Column(modifier = Modifier.selectableGroup()) {
        SettingsRadioButton(
            text = stringResource(id = R.string.light),
            selected = settings.themeConfig == ThemeConfig.LIGHT,
            onClick = { onChangeThemeConfig.invoke(ThemeConfig.LIGHT) }
        )
        SettingsRadioButton(
            text = stringResource(id = R.string.dark),
            selected = settings.themeConfig == ThemeConfig.DARK,
            onClick = { onChangeThemeConfig.invoke(ThemeConfig.DARK) }
        )
        SettingsRadioButton(
            text = stringResource(id = R.string.default_theme),
            selected = settings.themeConfig == ThemeConfig.DEFAULT,
            onClick = { onChangeThemeConfig.invoke(ThemeConfig.DEFAULT) }
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