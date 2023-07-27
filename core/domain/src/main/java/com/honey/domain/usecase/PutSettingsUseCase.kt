package com.honey.domain.usecase

import com.honyadew.model.EditableSettings
import com.honey.domain.repository.SettingsRepository

class PutSettingsUseCase(private val settingsRepository: SettingsRepository){
    fun invoke(settings: EditableSettings): Boolean {
        return settingsRepository.putUserSettings(editableSettings = settings)
    }
}