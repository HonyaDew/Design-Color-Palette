package com.honyadew.saved

import androidx.lifecycle.viewModelScope
import com.honyadew.base.BaseViewModel
import com.honyadew.domain.usecase.ChangeSchemeTitleUseCase
import com.honyadew.saved.contract.SavedEffect
import com.honyadew.saved.contract.SavedEvent
import com.honyadew.saved.contract.SavedState
import com.honyadew.saved.model.SavedTabs
import com.honyadew.domain.usecase.DeleteColorSchemeUseCase
import com.honyadew.domain.usecase.GetAllColorSchemeUseCase
import com.honyadew.model.CustomColorScheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class SavedViewModel(
    private val getAllSavedColorScheme: GetAllColorSchemeUseCase,
    private val deleteColorScheme: DeleteColorSchemeUseCase,
    private val changeSchemeTitle: ChangeSchemeTitleUseCase
) : BaseViewModel<SavedEvent, SavedState, SavedEffect>(initialState = SavedState.Loading) {

    private val allColorSchemesState = MutableStateFlow<List<CustomColorScheme>?>(null)

    init {
        viewModelScope.launch {
            allColorSchemesState.collect(){ colorSchemes ->
                colorSchemes?.let {
                    viewState = if (colorSchemes.isEmpty()){
                        SavedState.NoObjects
                    } else {
                        when (val state = viewState) {
                            is SavedState.Show -> {
                                state.copy(colorSchemes)
                            }
                            else -> {
                                SavedState.Show(colorSchemes, SavedTabs.ONE_COLOR)
                            }
                        }
                    }
                }
            }
        }

        //For tests
    }

    override fun obtainEvent(event: SavedEvent) {
        when(val state = viewState){
            is SavedState.Loading -> {reduce(event,state)}
            is SavedState.NoObjects -> {reduce(event,state)}
            is SavedState.Show -> {reduce(event,state)}
        }
    }

    private fun reduce(event: SavedEvent, state: SavedState.Loading) {}
    private fun reduce(event: SavedEvent, state: SavedState.NoObjects) {
        when(event){
            is SavedEvent.Refresh -> {
                //If want to add this, don't forget about UI
                loadColorSchemes()
            }
            else -> {}
        }
    }
    private fun reduce(event: SavedEvent, state: SavedState.Show) {
        when(event){
            is SavedEvent.DeleteColorScheme -> {
                performDeleteColor(event.colorScheme)
            }
            is SavedEvent.ChangeFilterTab -> {
                performChangeFilterTab(event.tab, state)
            }
            is SavedEvent.OpenColorScheme -> {
                performOpenScheme(state, event.colorScheme)
            }
            SavedEvent.CloseColorScheme -> {
                performOpenScheme(state)
            }
            is SavedEvent.SetNewTitle -> {
               performSetNewTitle(event.title, event.scheme)
            }
            else -> {}
        }
    }

    private fun performSetNewTitle(newTitle: String, scheme: CustomColorScheme) {
        viewModelScope.launch {
            changeSchemeTitle.invoke(newTitle, scheme)
            loadColorSchemes()
        }
    }

    private fun performDeleteColor(colorScheme: CustomColorScheme) {
        viewModelScope.launch {
            deleteColorScheme.invoke(colorScheme)
            loadColorSchemes()
        }
    }

    private fun performOpenScheme(state: SavedState.Show, colorScheme: CustomColorScheme? = null ) {
        viewState = state.copy(openedColorScheme = colorScheme)
    }

    private fun performChangeFilterTab(filterTab: SavedTabs, state: SavedState.Show){
        allColorSchemesState.value?.let { colorScheme ->
            viewState = state.copy(selectedTab = filterTab)
        }
    }

    // When user navigate to Saved Screen, this invoke
    fun loadColorSchemes() {
        viewModelScope.launch {
            allColorSchemesState.value = getAllSavedColorScheme.invoke()
        }
    }
}