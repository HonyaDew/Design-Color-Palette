package com.honey.domain.usecase

import com.honey.domain.repository.SettingsRepository
import com.honyadew.model.ColorOfMaterial
import com.honyadew.model.EditableSettings
import com.honyadew.model.Palette
import com.honyadew.model.ThemeConfig
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock

class PutSettingsUseCaseTest {
    private val settingsRepository = mock<SettingsRepository>()

    @Test
    fun `should return correct`() = runTest {
        val testSettings = EditableSettings(
            palette = Palette.Material(ColorOfMaterial.BLUE),
            themeConfig = ThemeConfig.DARK
        )
        Mockito.`when`(settingsRepository.putUserSettings(testSettings)).thenReturn(true)

        val useCase = PutSettingsUseCase(settingsRepository = settingsRepository)

        val actual = useCase.invoke(testSettings)
        val expected = true

        Assertions.assertEquals(expected, actual)
        Mockito.verify(settingsRepository, Mockito.times(1)).putUserSettings(testSettings)
    }
}