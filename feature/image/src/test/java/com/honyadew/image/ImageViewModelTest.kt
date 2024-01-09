package com.honyadew.image

import androidx.compose.ui.graphics.Color
import com.honyadew.domain.usecase.SaveColorSchemeUseCase
import com.honyadew.ArchTaskLooper
import com.honyadew.extencion.string
import com.honyadew.image.contract.ImageEvent
import com.honyadew.image.contract.ImageState
import com.honyadew.model.ColorInfo
import com.honyadew.model.CustomColorScheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock

@OptIn(ExperimentalCoroutinesApi::class)
class ImageViewModelTest {
    private val testDispatcher = UnconfinedTestDispatcher()
    private val saveColorSchemeUseCase = mock<SaveColorSchemeUseCase>()
    private lateinit var viewModel: ImageViewModel

    @BeforeEach
    fun setUp(){
        Dispatchers.setMain(testDispatcher)
        viewModel = ImageViewModel(
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

    @Test
    fun `should select color` () {
        viewModel.obtainEvent(ImageEvent.SelectColor(Color.Green))

        val expected = Color.Green
        val actual = (viewModel.getViewState().value as ImageState.Show).selectedColor

        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun `should move and remove from save list`() {
        val firstTestColor = ColorInfo(value = Color.Green.string(), name = "test")
        val secondTestColor = ColorInfo(value = Color.Yellow.string(), name = "test")

        viewModel.obtainEvent(ImageEvent.MoveToSave(firstTestColor))

        val actual = (viewModel.getViewState().value as ImageState.Show).colorsToSave
        val expected = listOf<ColorInfo>(firstTestColor)

        Assertions.assertEquals(expected, actual)

        viewModel.obtainEvent(ImageEvent.MoveToSave(secondTestColor))

        val actual2 = (viewModel.getViewState().value as ImageState.Show).colorsToSave
        val expected2 = listOf<ColorInfo>(firstTestColor, secondTestColor)

        Assertions.assertEquals(expected2, actual2)

        viewModel.obtainEvent(ImageEvent.RemoveFromToSave(firstTestColor))

        val actual3 = (viewModel.getViewState().value as ImageState.Show).colorsToSave
        val expected3 = listOf<ColorInfo>(secondTestColor)

        Assertions.assertEquals(expected3, actual3)
    }

    @Test
    fun `should send correct data and become empty`() = runTest {
        val testColor = ColorInfo(value = "value", name = "name")
        val testList = listOf<ColorInfo>(testColor)
        val testScheme = CustomColorScheme(colors = testList, name = "name")
        val state = viewModel.getViewState().value as ImageState.Show

        Mockito.`when`(saveColorSchemeUseCase.invoke(testScheme)).thenReturn(true)

        viewModel.obtainEvent(ImageEvent.MoveToSave(testColor))

        val actual = viewModel.getViewState().value
        val expected = state.copy(colorsToSave = testList)
        Assertions.assertEquals(expected, actual)

        viewModel.obtainEvent(ImageEvent.SaveColorScheme(testScheme))

        val actual2 = viewModel.getViewState().value
        val expected2 = state.copy(colorsToSave = emptyList())
        Assertions.assertEquals(expected2, actual2)

        Mockito.verify(saveColorSchemeUseCase, Mockito.times(1)).invoke(testScheme)
    }

}