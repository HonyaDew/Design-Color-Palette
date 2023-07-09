package com.honey.settings

import android.content.SharedPreferences
import com.honey.domain.model.ColorOfMaterial
import com.honey.domain.model.EditableSettings
import com.honey.domain.model.ThemeConfig
import com.honey.domain.model.Palette
import com.honey.domain.repository.SettingsRepository
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
        return jsonString?.let { Json.decodeFromString<EditableSettings>(jsonString) } ?:
        EditableSettings(
            palette = Palette.Material(subPalette = ColorOfMaterial.RED),
            themeConfig = ThemeConfig.LIGHT
        )
    }
}






