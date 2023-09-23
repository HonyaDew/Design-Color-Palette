package com.honyadew.domain.usecase

import com.honyadew.domain.repository.SavedRepository
import com.honyadew.model.ColorInfo
import com.honyadew.model.ColorSchemeSource
import com.honyadew.model.CustomColorScheme
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock

class DeleteColorSchemeUseCaseTest {
    private val savedRepository = mock<SavedRepository>()

    @Test
    fun `should send correct query to repository`() = runTest{
        val testData = CustomColorScheme(
            colors = listOf<ColorInfo>(),
            name = "Test",
            source = ColorSchemeSource.ExtractAuto
        )
        Mockito.`when`(savedRepository.deleteColorScheme(testData)).thenReturn(true)
        val useCase = DeleteColorSchemeUseCase(savedRepository = savedRepository)

        useCase.invoke(testData)

        Mockito.verify(savedRepository, Mockito.times(1)).deleteColorScheme(testData)
    }
}