package com.honyadew.domain.usecase

import com.honyadew.domain.repository.PaletteRepository
import com.honyadew.model.ColorInfo
import com.honyadew.model.Palette
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock

class GetColorByPaletteUseCaseTest {
    private val paletteRepository = mock<PaletteRepository>()

    @Test
    fun `should send correct palette`() = runTest {
        val testPalette = Palette.FlatUI
        val testList = listOf<ColorInfo>(ColorInfo(value = "1", name = "1", palette = testPalette))
        Mockito.`when`(paletteRepository.getColorsByPalette(testPalette)).thenReturn(testList)

        val useCase = GetColorByPaletteUseCase(paletteRepository = paletteRepository)

        val actual = useCase.invoke(testPalette)
        val expected = testList

        Assertions.assertEquals(expected, actual)
        Mockito.verify(paletteRepository, Mockito.times(1)).getColorsByPalette(testPalette)

    }
}