package com.honey.settings

import android.content.SharedPreferences
import com.honyadew.model.ColorOfMaterial
import com.honyadew.model.EditableSettings
import com.honyadew.model.ThemeConfig
import com.honyadew.model.Palette
import com.honey.domain.repository.SettingsRepository
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class SettingsRepositoryImpl (private val sharedPreferences: SharedPreferences): SettingsRepository {


    override fun putUserSettings(editableSettings: com.honyadew.model.EditableSettings): Boolean {
        val jsonString = Json.encodeToString(editableSettings)
        sharedPreferences.edit().putString("settings", jsonString).apply()
        return true
    }

    override fun getUserSettings(): com.honyadew.model.EditableSettings {
        val jsonString = sharedPreferences.getString("settings", null)
        return jsonString?.let { Json.decodeFromString<com.honyadew.model.EditableSettings>(jsonString) } ?: com.honyadew.model.EditableSettings(
            palette = com.honyadew.model.Palette.Material(subPalette = com.honyadew.model.ColorOfMaterial.RED),
            themeConfig = com.honyadew.model.ThemeConfig.LIGHT
        )
    }
}






