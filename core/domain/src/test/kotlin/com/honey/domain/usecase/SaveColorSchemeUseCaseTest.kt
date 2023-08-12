package com.honey.domain.usecase

import com.honey.domain.repository.SavedRepository
import com.honyadew.model.ColorSchemeSource
import com.honyadew.model.CustomColorScheme
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.kotlin.mock

class SaveColorSchemeUseCaseTest {
    val savedRepository = mock<SavedRepository>()

    @Test
    fun `should send correct and return true`() = runTest {
        val testColorScheme = CustomColorScheme(
            colors = listOf(),
            name = "test",
            source = ColorSchemeSource.ExtractManual
        )
        Mockito.`when`(savedRepository.saveColorScheme(testColorScheme)).thenReturn(true)

        val useCase = SaveColorSchemeUseCase(savedRepository = savedRepository)

        val actual = useCase.invoke(testColorScheme)
        val expected = true

        Assertions.assertEquals(expected, actual)
        Mockito.verify(savedRepository, Mockito.times(1)).saveColorScheme(testColorScheme)
    }
}