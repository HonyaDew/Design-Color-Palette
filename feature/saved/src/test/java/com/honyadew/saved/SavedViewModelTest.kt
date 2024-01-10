package com.honyadew.saved

import com.honyadew.domain.usecase.DeleteColorSchemeUseCase
import com.honyadew.domain.usecase.GetAllColorSchemeUseCase
import com.honyadew.ArchTaskLooper
import com.honyadew.domain.usecase.ChangeSchemeTitleUseCase
import com.honyadew.model.ColorInfo
import com.honyadew.model.CustomColorScheme
import com.honyadew.saved.contract.SavedEvent
import com.honyadew.saved.contract.SavedState
import com.honyadew.saved.model.SavedTabs
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
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
    private val changeSchemeTitleUseCase = mock<ChangeSchemeTitleUseCase>()
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
        Mockito.reset(changeSchemeTitleUseCase)
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
            getAllColorSchemeUseCase, deleteColorSchemeUseCase, changeSchemeTitleUseCase
        )
        viewModel.loadColorSchemes()

        delay(1000)

        val expected = SavedState.Show(allSchemes = testData, selectedTab = SavedTabs.ONE_COLOR)
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
        viewModel = SavedViewModel(getAllColorSchemeUseCase, deleteColorSchemeUseCase, changeSchemeTitleUseCase)
        viewModel.loadColorSchemes()

        Mockito.`when`(getAllColorSchemeUseCase.invoke()).thenReturn(emptyList())
        viewModel.obtainEvent(event = SavedEvent.DeleteColorScheme(testData))


        val expected = SavedState.NoObjects
        val actual = viewModel.getViewState().value

        Assertions.assertEquals(expected, actual)
        Mockito.verify(getAllColorSchemeUseCase, times(2)).invoke()
        Mockito.verify(deleteColorSchemeUseCase, times(1)).invoke(testData)
    }


}