package com.honyadew.designcolorpalette

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.honyadew.GlobalSignals
import com.honyadew.base.ViewState
import com.honyadew.domain.usecase.GetSettingsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainActivityViewModel(
    private val getSettings: GetSettingsUseCase
) : ViewModel() {
    private val _viewState = MutableStateFlow<MainActivityState>(MainActivityState.Loading)
    val viewState : StateFlow<MainActivityState> = _viewState.asStateFlow()

    init {
        viewModelScope.launch {
            GlobalSignals.showSettingsState.collect(){
                _viewState.value = MainActivityState.Show(editableSettings = getSettings.invoke())
            }
        }
    }

}

sealed class MainActivityState : ViewState {
    object Loading : MainActivityState()
    data class Show(val editableSettings: com.honyadew.model.EditableSettings) : MainActivityState()
}