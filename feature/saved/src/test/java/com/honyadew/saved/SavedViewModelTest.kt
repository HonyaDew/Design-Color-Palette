package com.honyadew.saved

import androidx.lifecycle.viewmodel.compose.viewModel
import com.honey.domain.usecase.DeleteColorSchemeUseCase
import com.honey.domain.usecase.GetAllColorSchemeUseCase
import com.honyadew.ArchTaskLooper
import com.honyadew.model.ColorInfo
import com.honyadew.model.CustomColorScheme
import com.honyadew.saved.contact.SavedEvent
import com.honyadew.saved.contact.SavedState
import com.honyadew.saved.model.SavedTabs
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
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
import org.mockito.kotlin.times

@OptIn(ExperimentalCoroutinesApi::class)
class SavedViewModelTest {
    private val testDispatcher = UnconfinedTestDispatcher()
    private val getAllColorSchemeUseCase = mock<GetAllColorSchemeUseCase>()
    private val deleteColorSchemeUseCase = mock<DeleteColorSchemeUseCase>()
    private lateinit var viewModel : SavedViewModel

    @BeforeEach
    fun setUp(){
        Dispatchers.setMain(testDispatcher)
        ArchTaskLooper().before()
    }

    @AfterEach
    fun tearDown(){
        Dispatchers.resetMain()
        Mockito.reset(getAllColorSchemeUseCase)
        Mockito.reset(deleteColorSchemeUseCase)
        ArchTaskLooper().after()
    }

    @Test
    fun `should just load`() = runTest {
        val testData = listOf<CustomColorScheme>(
            CustomColorScheme(colors = listOf(
                ColorInfo(value = "t", name = "t")
            ), name = "t"),
            CustomColorScheme(colors = listOf(
                ColorInfo(value = "t2", name = "t2"),
                ColorInfo(value = "t3", name = "t3")
            ), name = "t2")
        )
        Mockito.`when`(getAllColorSchemeUseCase.invoke()).thenReturn(testData)
        viewModel = SavedViewModel(
            getAllColorSchemeUseCase, deleteColorSchemeUseCase
        )
        delay(1000)

        val expected = SavedState.Show(colorsToShow = listOf(testData[0]), selectedTab = SavedTabs.ONE_COLOR)
        val actual = viewModel.getViewState().value

        Assertions.assertEquals(expected, actual)
        Mockito.verify(getAllColorSchemeUseCase, times(1)).invoke()
    }

    @Test
    fun `should load and then delete one item`() = runTest{
        val testData = CustomColorScheme(
            colors = listOf(ColorInfo(value = "t", name = "t")), name = "t"
        )
        Mockito.`when`(getAllColorSchemeUseCase.invoke()).thenReturn(listOf(testData))
        viewModel = SavedViewModel(getAllColorSchemeUseCase, deleteColorSchemeUseCase)

        Mockito.`when`(getAllColorSchemeUseCase.invoke()).thenReturn(emptyList())
        viewModel.obtainEvent(event = SavedEvent.DeleteColorScheme(testData))

        val expected = SavedState.NoObjects
        val actual = viewModel.getViewState().value

        Assertions.assertEquals(expected, actual)
        Mockito.verify(getAllColorSchemeUseCase, times(2)).invoke()
        Mockito.verify(deleteColorSchemeUseCase, times(1)).invoke(testData)
    }

    @Test
    fun `should change tab and filter`() = runTest {
        val testData = listOf<CustomColorScheme>(
            CustomColorScheme(colors = listOf(
                ColorInfo(value = "t", name = "t")
            ), name = "t"),
            CustomColorScheme(colors = listOf(
                ColorInfo(value = "t2", name = "t2"),
                ColorInfo(value = "t3", name = "t3")
            ), name = "t2"),
            CustomColorScheme(colors = listOf(
                ColorInfo(value = "t4", name = "t4"),
                ColorInfo(value = "t5", name = "t5"),
                ColorInfo(value = "t6", name = "t6"),
                ColorInfo(value = "t7", name = "t7"),
                ColorInfo(value = "t8", name = "t8")
            ), name = "t3"),
        )

        Mockito.`when`(getAllColorSchemeUseCase.invoke()).thenReturn(testData)
        viewModel = SavedViewModel(
            getAllColorSchemeUseCase, deleteColorSchemeUseCase
        )

        val expected = testData[0]
        val actual = (viewModel.getViewState().value as SavedState.Show).colorsToShow[0]
        Assertions.assertEquals(expected, actual)

        viewModel.obtainEvent(SavedEvent.ChangeFilterTab(tab = SavedTabs.SMALL_SCHEME))

        val expected2 = testData[1]
        val actual2 = (viewModel.getViewState().value as SavedState.Show).colorsToShow[0]
        Assertions.assertEquals(expected2, actual2)

        viewModel.obtainEvent(SavedEvent.ChangeFilterTab(tab = SavedTabs.BIG_SCHEME))

        val expected3 = testData[2]
        val actual3 = (viewModel.getViewState().value as SavedState.Show).colorsToShow[0]
        Assertions.assertEquals(expected3, actual3)

        Mockito.verify(getAllColorSchemeUseCase, times(1)).invoke()
    }
}