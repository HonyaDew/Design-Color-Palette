package com.honyadew.saved

import androidx.lifecycle.viewModelScope
import com.honyadew.base.BaseViewModel
import com.honyadew.saved.contact.SavedEffect
import com.honyadew.saved.contact.SavedEvent
import com.honyadew.saved.contact.SavedState
import com.honyadew.saved.model.SavedTabs
import com.honey.domain.usecase.DeleteColorSchemeUseCase
import com.honey.domain.usecase.FilterColorSchemeUseCase
import com.honey.domain.usecase.GetAllColorSchemeUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class SavedViewModel(
    private val getAllSavedColorScheme: GetAllColorSchemeUseCase,
    private val filterColorScheme: FilterColorSchemeUseCase,
    private val deleteColorScheme: DeleteColorSchemeUseCase
) : BaseViewModel<SavedEvent, SavedState, SavedEffect>(initialState = SavedState.Loading) {

    private val allColorSchemesState = MutableStateFlow<List<com.honyadew.model.CustomColorScheme>?>(null)

    init {
        setObserver()
    }

    override fun obtainEvent(event: SavedEvent) {
        when(val state = viewState){
            is SavedState.Loading -> {reduce(event,state)}
            is SavedState.Show -> {reduce(event,state)}
        }
    }

    private fun reduce(event: SavedEvent, state: SavedState.Loading) {}
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
        }
    }

    private fun performDeleteColor(colorScheme: com.honyadew.model.CustomColorScheme) {
        viewModelScope.launch {
            deleteColorScheme.invoke(colorScheme)
            loadColorSchemes()
        }
    }

    private fun performOpenScheme(state: SavedState.Show, colorScheme: com.honyadew.model.CustomColorScheme? = null ) {
        viewState = state.copy(openedColorScheme = colorScheme)
    }

    private fun performChangeFilterTab(filterTab: SavedTabs, state: SavedState.Show){
        allColorSchemesState.value?.let { colorScheme ->
            val filtered = filterColorScheme.invoke(colorScheme, filterTab.filter)
            viewState = state.copy(colorsToShow = filtered, selectedTab = filterTab)
        }
    }


    private fun setObserver(){
        viewModelScope.launch {
            allColorSchemesState.collect(){ allColorSchemes ->
                allColorSchemes?.let {
                    when(val state = viewState){
                        is SavedState.Show -> {
                            val filtered = filterColorScheme.invoke(allColorSchemes, state.selectedTab.filter)
                            viewState = state.copy(filtered)
                        }
                        else -> {
                            val filtered = filterColorScheme.invoke(allColorSchemes, com.honyadew.model.ColorSchemeFilters.SingleColor)
                            viewState = SavedState.Show(filtered, SavedTabs.ONE_COLOR)
                        }
                    }
                }
            }
        }
    }


    fun loadColorSchemes() {
        viewModelScope.launch {
            allColorSchemesState.value = getAllSavedColorScheme.invoke()
        }
    }
}