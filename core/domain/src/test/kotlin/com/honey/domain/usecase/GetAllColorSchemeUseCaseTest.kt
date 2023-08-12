package com.honey.domain.usecase

import com.honey.domain.repository.SavedRepository
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock
import org.mockito.kotlin.times

class GetAllColorSchemeUseCaseTest {
    private val savedRepository = mock<SavedRepository>()

    @Test
    fun `should call once and return correct`() = runTest{
        Mockito.`when`(savedRepository.getAllSavedSchemes()).thenReturn(testList)
        val useCase = GetAllColorSchemeUseCase(savedRepository = savedRepository)

        val actual = useCase.invoke()
        val expected = testList

        Assertions.assertEquals(expected, actual)
        Mockito.verify(savedRepository, Mockito.times(1)).getAllSavedSchemes()
    }
}