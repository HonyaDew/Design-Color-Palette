package com.honyadew.domain.usecase

import com.honyadew.domain.repository.SavedRepository
import com.honyadew.model.ColorInfo
import com.honyadew.model.CustomColorScheme
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock
import java.awt.Color

class GetAllColorSchemeUseCaseTest {
    private val savedRepository = mock<SavedRepository>()

    @Test
    fun `should call once and return correct`() = runTest{
        val testList = listOf(
            CustomColorScheme(listOf(), "test")
        )
        Mockito.`when`(savedRepository.getAllSavedSchemes()).thenReturn(testList)
        val useCase = GetAllColorSchemeUseCase(savedRepository = savedRepository)

        val actual = useCase.invoke()
        val expected = testList

        Assertions.assertEquals(expected, actual)
        Mockito.verify(savedRepository, Mockito.times(1)).getAllSavedSchemes()
    }
}