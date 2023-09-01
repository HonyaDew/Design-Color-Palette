package com.honyadew.palette

import com.honey.domain.usecase.GetColorByPaletteUseCase
import com.honey.domain.usecase.GetSettingsUseCase
import com.honey.domain.usecase.PutSettingsUseCase
import org.mockito.kotlin.mock

class PaletteViewModelTest {
    private val getSettingsUseCase = mock<GetSettingsUseCase>()
    private val putSettingsUseCase = mock<PutSettingsUseCase>()
    private val getColorByPaletteUseCase = mock<GetColorByPaletteUseCase>()
    private lateinit var viewModel: PaletteViewModel

}