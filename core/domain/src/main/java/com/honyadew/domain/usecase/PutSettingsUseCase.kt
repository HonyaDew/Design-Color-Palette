package com.honyadew.domain.usecase

import com.honyadew.model.EditableSettings
import com.honyadew.domain.repository.SettingsRepository

class PutSettingsUseCase(private val settingsRepository: SettingsRepository){
    fun invoke(settings: EditableSettings): Boolean {
        return settingsRepository.putUserSettings(editableSettings = settings)
    }
}