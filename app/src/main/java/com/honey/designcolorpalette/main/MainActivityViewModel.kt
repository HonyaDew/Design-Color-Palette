package com.honey.designcolorpalette.main

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.honey.designcolorpalette.base.ViewState
import com.honey.domain.model.EditableSettings
import com.honey.domain.usecase.GetSettingsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class MainActivityViewModel(
    private val getSettings: GetSettingsUseCase
) : ViewModel() {
    private val _viewState = MutableStateFlow<MainActivityState>(MainActivityState.Loading)
    val viewState : StateFlow<MainActivityState> = _viewState.asStateFlow()

    init {
        _viewState.value = MainActivityState.Show(editableSettings = getSettings.invoke())

        viewModelScope.launch {
            showSettingsState.collect(){
                _viewState.value = MainActivityState.Show(editableSettings = getSettings.invoke())
            }
        }
    }

}

sealed class MainActivityState : ViewState {
    object Loading : MainActivityState()
    data class Show(val editableSettings: EditableSettings) : MainActivityState()
}