package com.honey.domain.usecase

import com.honyadew.model.ColorInfo
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test


class AddColorToListUseCaseTest {

    @Test
    fun `should return list with newElement`(){
        val initialList = List<ColorInfo>(
            size = 4, init = {index -> ColorInfo(value = "123", name = "$index") }
        )
        val newElement = ColorInfo(value = "123", name = "4")
        val useCase = AddColorToListUseCase()

        val actualList = useCase.invoke(list = initialList, color = newElement)

        val expectedList = List<ColorInfo>(
            size = 5, init = {index -> ColorInfo(value = "123", name = "$index") }
        )
        Assertions.assertEquals(expectedList, actualList)
    }
}