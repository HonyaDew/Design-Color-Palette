package com.honyadew.sliders

import com.honyadew.domain.usecase.SaveColorSchemeUseCase
import com.honyadew.ArchTaskLooper
import com.honyadew.model.ColorInfo
import com.honyadew.model.CustomColorScheme
import com.honyadew.sliders.contract.SlidersEvent
import com.honyadew.sliders.contract.SlidersState
import com.honyadew.sliders.model.SlidersType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import org.mockito.Mockito
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.mockito.kotlin.mock

@OptIn(ExperimentalCoroutinesApi::class)
class SlidersViewModelTest {
    private val testDispatcher = UnconfinedTestDispatcher()
    private val saveColorSchemeUseCase = mock<SaveColorSchemeUseCase>()
    private lateinit var viewModel: SlidersViewModel

    @BeforeEach()
    fun setUp(){
        Dispatchers.setMain(testDispatcher)
        viewModel = SlidersViewModel(
            saveColorScheme = saveColorSchemeUseCase
        )
        ArchTaskLooper().before()
    }

    @AfterEach
    fun tearDown(){
        Dispatchers.resetMain()
        Mockito.reset(saveColorSchemeUseCase)
        ArchTaskLooper().after()
    }

    @ParameterizedTest
    @ValueSource(floats = [0.19f, 0.21f, 0.88f])
    fun `should set newValues to sliders`(newValue : Float){
        val first = newValue + 0.01f
        val second = newValue + 0.02f
        val third = newValue + 0.03f
        val forth = newValue + 0.04f

        viewModel.obtainEvent(SlidersEvent.SetFirstSliderValue(first))
        viewModel.obtainEvent(SlidersEvent.SetSecondSliderValue(second))
        viewModel.obtainEvent(SlidersEvent.SetThirdSliderValue(third))
        viewModel.obtainEvent(SlidersEvent.SetAlphaSliderValue(forth))

        val actual = viewModel.getViewState().value
        val expected = SlidersState.Show(
            SlidersType.RGB,
            sliderOne = first,
            sliderTwo = second,
            sliderThree = third,
            sliderAlpha = forth
        )

        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun `should send correct data and become empty`() = runTest {
        val testColor = ColorInfo(value = "value", name = "name")
        val testList = listOf<ColorInfo>(testColor)
        val testScheme = CustomColorScheme(colors = testList, name = "name")

        Mockito.`when`(saveColorSchemeUseCase.invoke(testScheme)).thenReturn(true)

        viewModel.obtainEvent(SlidersEvent.AddColorToSaveList(testColor))

        val actual = viewModel.getViewState().value
        val expected = SlidersState.Show(type = SlidersType.RGB, colorsToSave = testList)
        Assertions.assertEquals(expected, actual)

        viewModel.obtainEvent(SlidersEvent.SaveColorScheme(testScheme))

        val actual2 = viewModel.getViewState().value
        val expected2 = SlidersState.Show(type = SlidersType.RGB, colorsToSave = emptyList())
        Assertions.assertEquals(expected2, actual2)

        Mockito.verify(saveColorSchemeUseCase, Mockito.times(1)).invoke(testScheme)
    }

    @Test
    fun `should select HSV and RGB`(){
        viewModel.obtainEvent(SlidersEvent.SelectHSV)
        val actual = viewModel.getViewState().value
        val expected = SlidersState.Show(type = SlidersType.HSV)
        Assertions.assertEquals(expected, actual)

        viewModel.obtainEvent(SlidersEvent.SelectRGB)
        val actual2 = viewModel.getViewState().value
        val expected2 = SlidersState.Show(type = SlidersType.RGB)
        Assertions.assertEquals(expected2, actual2)
    }
}