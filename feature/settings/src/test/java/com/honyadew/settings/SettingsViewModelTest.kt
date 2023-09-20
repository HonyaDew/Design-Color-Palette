package com.honyadew.settings

import com.honey.domain.usecase.GetSettingsUseCase
import com.honey.domain.usecase.PutSettingsUseCase
import com.honyadew.ArchTaskLooper
import com.honyadew.model.EditableSettings
import com.honyadew.model.Palette
import com.honyadew.model.ThemeConfig
import com.honyadew.settings.contract.SettingsEvent
import com.honyadew.settings.contract.SettingsState
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.times

class SettingsViewModelTest {
    private val getSettingsUseCase = mock<GetSettingsUseCase>()
    private val putSettingsUseCase = mock<PutSettingsUseCase>()
    private lateinit var viewModel: SettingsViewModel

    @BeforeEach
    fun setUp(){

        ArchTaskLooper().before()
    }

    @AfterEach
    fun tearDown(){
        Mockito.reset(getSettingsUseCase)
        Mockito.reset(putSettingsUseCase)
        ArchTaskLooper().after()
    }

    @Test
    fun `should load`(){
        val testSettings = EditableSettings(
            palette = Palette.Metro,
            themeConfig = ThemeConfig.LIGHT
        )
        Mockito.`when`(getSettingsUseCase.invoke()).thenReturn(testSettings)
        viewModel = SettingsViewModel(
            getSettings = getSettingsUseCase,
            putSettings = putSettingsUseCase
        )

        val expected = SettingsState.Show(settings = testSettings)
        val actual = viewModel.getViewState().value

        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun `should load, save and change`(){
        val testInitialSettings = EditableSettings(
            palette = Palette.Metro,
            themeConfig = ThemeConfig.LIGHT
        )
        val testEndSettings = EditableSettings(
            palette = Palette.Metro,
            themeConfig = ThemeConfig.DARK
        )

        Mockito.`when`(getSettingsUseCase.invoke()).thenReturn(testInitialSettings)
        Mockito.`when`(putSettingsUseCase.invoke(any())).thenReturn(true)

        viewModel = SettingsViewModel(
            getSettings = getSettingsUseCase,
            putSettings = putSettingsUseCase
        )

        val firstEvent = SettingsEvent.SelectPalette(Palette.Metro)
        val secondEvent = SettingsEvent.SelectTheme(ThemeConfig.DARK)

        viewModel.obtainEvent(firstEvent)
        viewModel.obtainEvent(secondEvent)

        val expected = SettingsState.Show(testEndSettings)
        val actual = viewModel.getViewState().value

        Assertions.assertEquals(expected, actual)
        Mockito.verify(getSettingsUseCase, times(1)).invoke()
        Mockito.verify(putSettingsUseCase, times(1)).invoke(
            testInitialSettings.copy(palette = testEndSettings.palette)
        )
        Mockito.verify(putSettingsUseCase, times(1)).invoke(testEndSettings)
    }
}