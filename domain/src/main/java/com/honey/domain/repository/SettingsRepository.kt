package com.honey.domain.repository

import com.honey.domain.model.EditableSettings

interface SettingsRepository {
    fun putUserSettings(editableSettings: EditableSettings): Boolean
    fun getUserSettings(): EditableSettings
}