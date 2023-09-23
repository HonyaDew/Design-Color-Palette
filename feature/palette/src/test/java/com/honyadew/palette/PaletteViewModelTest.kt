package com.honyadew.palette

import com.honyadew.domain.usecase.GetColorByPaletteUseCase
import com.honyadew.domain.usecase.GetSettingsUseCase
import com.honyadew.domain.usecase.PutSettingsUseCase
import com.honyadew.ArchTaskLooper
import com.honyadew.model.ColorInfo
import com.honyadew.model.ColorOfMaterial
import com.honyadew.model.EditableSettings
import com.honyadew.model.Palette
import com.honyadew.model.ThemeConfig
import com.honyadew.palette.contract.PaletteEvent
import com.honyadew.palette.contract.PaletteState
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock
import org.mockito.kotlin.times

class PaletteViewModelTest {
    private val getSettingsUseCase = mock<GetSettingsUseCase>()
    private val putSettingsUseCase = mock<PutSettingsUseCase>()
    private val getColorByPaletteUseCase = mock<GetColorByPaletteUseCase>()
    private lateinit var viewModel: PaletteViewModel

    @BeforeEach
    fun setUp(){
        viewModel = PaletteViewModel(
            getSettings = getSettingsUseCase,
            putSettings = putSettingsUseCase,
            getColorsByPalette = getColorByPaletteUseCase
        )
        ArchTaskLooper().before()
    }
    @AfterEach
    fun tearDown(){
        Mockito.reset(getSettingsUseCase)
        Mockito.reset(putSettingsUseCase)
        Mockito.reset(getColorByPaletteUseCase)
        ArchTaskLooper().after()
    }

    @Test
    fun `should just load`() {
        val testSettings = EditableSettings(palette = Palette.HTML, ThemeConfig.LIGHT)
        Mockito.`when`(getSettingsUseCase.invoke()).thenReturn(testSettings)

        viewModel.obtainEvent(PaletteEvent.UpdatePalette)

        val expected = testSettings.palette
        val actual = (viewModel.getViewState().value as PaletteState.Show).palette
        Assertions.assertEquals(expected, actual)

        val testSettings2 = EditableSettings(palette = Palette.Metro, ThemeConfig.DARK)
        Mockito.`when`(getSettingsUseCase.invoke()).thenReturn(testSettings2)

        viewModel.obtainEvent(PaletteEvent.UpdatePalette)

        val expected2 = testSettings2.palette
        val actual2 = (viewModel.getViewState().value as PaletteState.Show).palette
        Assertions.assertEquals(expected2, actual2)

        Mockito.verify(getColorByPaletteUseCase, times(1)).invoke(Palette.HTML)
        Mockito.verify(getColorByPaletteUseCase, times(1)).invoke(Palette.Metro)
    }

    @Test
    fun `should load and later save`(){
        val testSettings = EditableSettings(
            palette = Palette.Material(ColorOfMaterial.AMBER), ThemeConfig.LIGHT
        )
        Mockito.`when`(getSettingsUseCase.invoke()).thenReturn(testSettings)
        Mockito.`when`(putSettingsUseCase.invoke(
            testSettings.copy(palette = Palette.Material(ColorOfMaterial.GREEN)))
        ).thenReturn(true)

        viewModel.obtainEvent(PaletteEvent.UpdatePalette)
        Mockito.`when`(getSettingsUseCase.invoke()).thenReturn(
            testSettings.copy(palette = Palette.Material(ColorOfMaterial.GREEN))
        )
        viewModel.obtainEvent(PaletteEvent.SelectSubPalette(Palette.Material(ColorOfMaterial.GREEN)))

        val expected = Palette.Material(ColorOfMaterial.GREEN)
        val actual = (viewModel.getViewState().value as PaletteState.Show).palette
        Assertions.assertEquals(expected, actual)

        Mockito.verify(putSettingsUseCase, times(1)).invoke(
            testSettings.copy(palette = Palette.Material(ColorOfMaterial.GREEN))
        )

    }

    @Test
    fun `should get colors by palette`() {
        val testPalette = Palette.Material(ColorOfMaterial.AMBER)
        val testColorInfo = ColorInfo(value = "test", name = "test")
        Mockito.`when`(getSettingsUseCase.invoke()).thenReturn(
            EditableSettings(testPalette, ThemeConfig.LIGHT)
        )
        Mockito.`when`(getColorByPaletteUseCase.invoke(testPalette)).thenReturn(
            listOf(testColorInfo)
        )

        viewModel.obtainEvent(PaletteEvent.UpdatePalette)

        val expected = listOf(testColorInfo)
        val actual = (viewModel.getViewState().value as PaletteState.Show).colorsToShow

        Assertions.assertEquals(expected, actual)
        Mockito.verify(getColorByPaletteUseCase, times(1)).invoke(testPalette)
    }
}