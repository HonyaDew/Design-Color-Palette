package com.honey.domain.repository

import com.honyadew.model.EditableSettings

interface SettingsRepository {
    fun putUserSettings(editableSettings: EditableSettings): Boolean
    fun getUserSettings(): EditableSettings
}