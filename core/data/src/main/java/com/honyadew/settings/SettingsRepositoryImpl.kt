package com.honyadew.settings

import android.content.SharedPreferences
import com.honyadew.model.EditableSettings
import com.honyadew.domain.repository.SettingsRepository
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class SettingsRepositoryImpl (private val sharedPreferences: SharedPreferences): SettingsRepository {


    override fun putUserSettings(editableSettings: EditableSettings): Boolean {
        val jsonString = Json.encodeToString(editableSettings)
        sharedPreferences.edit().putString("settings", jsonString).apply()
        return true
    }

    override fun getUserSettings(): EditableSettings {
        val jsonString = sharedPreferences.getString("settings", null)
        return jsonString?.let { Json.decodeFromString<EditableSettings>(jsonString) } ?: EditableSettings(
            palette = com.honyadew.model.Palette.Material(subPalette = com.honyadew.model.ColorOfMaterial.RED),
            themeConfig = com.honyadew.model.ThemeConfig.LIGHT
        )
    }
}






