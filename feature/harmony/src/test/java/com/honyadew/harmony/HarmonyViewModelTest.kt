package com.honyadew.harmony

import androidx.compose.ui.graphics.Color
import com.honyadew.domain.usecase.SaveColorSchemeUseCase
import com.honyadew.ArchTaskLooper
import com.honyadew.harmony.contract.HarmonyEvent
import com.honyadew.harmony.contract.HarmonyMode
import com.honyadew.harmony.contract.HarmonyState
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
class HarmonyViewModelTest {
    private val testDispatcher = UnconfinedTestDispatcher()
    private val saveColorSchemeUseCase = mock<SaveColorSchemeUseCase>()
    private lateinit var viewModel: HarmonyViewModel

    @BeforeEach
    fun setUp(){
        Dispatchers.setMain(testDispatcher)
        viewModel = HarmonyViewModel(
            saveColorScheme = saveColorSchemeUseCase
        )
        ArchTaskLooper().before()
    }

    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
        Mockito.reset(saveColorSchemeUseCase)
        ArchTaskLooper().after()
    }

    @Test
    fun `should changeHarmonyValue` () {
        val state = HarmonyState.Show()
        val event = HarmonyEvent.ChangeHarmonyValue(
            newValue = Color.Green,
            harmonyMode = HarmonyMode.SPLIT_COMPLEMENTARY
        )
        viewModel.obtainEvent(event)

        val arraylist = ArrayList(state.harmoniesValue)
        arraylist[HarmonyMode.values().indexOf(event.harmonyMode)] = event.newValue
        val expected = state.copy(harmoniesValue = arraylist.toList())
        val actual = viewModel.getViewState().value

        Assertions.assertEquals(expected, actual)

        val secondState = viewModel.getViewState().value as HarmonyState.Show
        val secondEvent = HarmonyEvent.ChangeHarmonyValue(
            newValue = Color.Yellow,
            harmonyMode = HarmonyMode.TETRADIC
        )
        viewModel.obtainEvent(secondEvent)

        val arraylist2 = ArrayList(secondState.harmoniesValue)
        arraylist2[HarmonyMode.values().indexOf(secondEvent.harmonyMode)] = secondEvent.newValue
        val expected2 = secondState.copy(harmoniesValue = arraylist2.toList())
        val actual2 = viewModel.getViewState().value

        Assertions.assertEquals(expected2,actual2)
    }

    @Test
    fun `should send correct data` () = runTest{

        val testColor = ColorInfo(value = "value", name = "name")
        val testList = listOf<ColorInfo>(testColor)
        val testScheme = CustomColorScheme(colors = testList, name = "name")

        val event = HarmonyEvent.SaveColorScheme(colorScheme = testScheme)

        Mockito.`when`(saveColorSchemeUseCase.invoke(testScheme)).thenReturn(true)

        viewModel.obtainEvent(event)

        Mockito.verify(saveColorSchemeUseCase, Mockito.times(1)).invoke(testScheme)
    }

    @Test
    fun `should swap copy mode` (){
        val event = HarmonyEvent.SwapCopyMode
        val state = viewModel.getViewState().value as HarmonyState.Show

        viewModel.obtainEvent(event)

        val expected = state.copy(hexAsCopyMode = !state.hexAsCopyMode)
        val actual = viewModel.getViewState().value

        Assertions.assertEquals(expected, actual)

        viewModel.obtainEvent(event)

        val expected2 = state.copy(hexAsCopyMode = state.hexAsCopyMode)
        val actual2 = viewModel.getViewState().value

        Assertions.assertEquals(expected2, actual2)
    }
}