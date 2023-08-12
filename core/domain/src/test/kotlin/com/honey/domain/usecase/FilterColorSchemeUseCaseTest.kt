package com.honey.domain.usecase

import com.honyadew.model.ColorInfo
import com.honyadew.model.ColorSchemeFilters
import com.honyadew.model.CustomColorScheme
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

val testList = listOf<CustomColorScheme>(
    CustomColorScheme(
        colors = List<ColorInfo>(
            size = 1, init = {index -> ColorInfo(value = "123", name = "$index") }),
        name = "Single1"
    ),
    CustomColorScheme(
        colors = List<ColorInfo>(
            size = 3, init = {index -> ColorInfo(value = "123", name = "$index") }),
        name = "Triple1"
    ),
    CustomColorScheme(
        colors = List<ColorInfo>(
            size = 3, init = {index -> ColorInfo(value = "123", name = "$index") }),
        name = "Triple2"
    ),
    CustomColorScheme(
        colors = List<ColorInfo>(
            size = 9, init = {index -> ColorInfo(value = "123", name = "$index") }),
        name = "Nine-ple1"
    ),
    CustomColorScheme(
        colors = List<ColorInfo>(
            size = 9, init = {index -> ColorInfo(value = "123", name = "$index") }),
        name = "Nine-ple2"
    ),
    CustomColorScheme(
        colors = List<ColorInfo>(
            size = 9, init = {index -> ColorInfo(value = "123", name = "$index") }),
        name = "Nine-ple3"
    ),
)

class FilterColorSchemeUseCaseTest {

    @Test
    fun `should return only single schemes`() {
        val filter = ColorSchemeFilters.SingleColor

        val useCase = FilterColorSchemeUseCase()

        val result = useCase.invoke(colorSchemes = testList, filter = filter)

        val actual = result.size
        val expected = 1

        Assertions.assertEquals(expected, actual)
    }
    @Test
    fun `should return only 2-4 schemes`() {
        val filter = ColorSchemeFilters.UpToFourColors

        val useCase = FilterColorSchemeUseCase()

        val result = useCase.invoke(colorSchemes = testList, filter = filter)

        val actual = result.size
        val expected = 2

        Assertions.assertEquals(expected, actual)
    }
    @Test
    fun `should return only multi schemes`() {
        val filter = ColorSchemeFilters.MultiColors

        val useCase = FilterColorSchemeUseCase()

        val result = useCase.invoke(colorSchemes = testList, filter = filter)

        val actual = result.size
        val expected = 3

        Assertions.assertEquals(expected, actual)
    }
}