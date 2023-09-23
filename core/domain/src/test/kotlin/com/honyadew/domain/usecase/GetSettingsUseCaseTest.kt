package com.honyadew.domain.usecase

import com.honyadew.domain.repository.SettingsRepository
import com.honyadew.model.ColorOfMaterial
import com.honyadew.model.EditableSettings
import com.honyadew.model.Palette
import com.honyadew.model.ThemeConfig
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock

class GetSettingsUseCaseTest {
    private val settingsRepository = mock<SettingsRepository>()

    @Test
    fun `should return correct`() = runTest {
        val testSettings = EditableSettings(
            palette = Palette.Material(ColorOfMaterial.BLUE),
            themeConfig = ThemeConfig.DARK
        )
        Mockito.`when`(settingsRepository.getUserSettings()).thenReturn(testSettings)

        val useCase = GetSettingsUseCase(settingsRepository = settingsRepository)

        val actual = useCase.invoke()
        val expected = testSettings

        Assertions.assertEquals(expected, actual)
        Mockito.verify(settingsRepository, Mockito.times(1)).getUserSettings()
    }
}