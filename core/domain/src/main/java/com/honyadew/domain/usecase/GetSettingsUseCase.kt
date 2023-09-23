package com.honyadew.domain.usecase

import com.honyadew.model.EditableSettings
import com.honyadew.domain.repository.SettingsRepository

class GetSettingsUseCase(private val settingsRepository: SettingsRepository) {
    fun invoke(): EditableSettings {
        return settingsRepository.getUserSettings()
    }
}