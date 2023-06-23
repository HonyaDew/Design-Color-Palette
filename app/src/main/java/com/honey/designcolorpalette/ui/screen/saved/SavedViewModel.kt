package com.honey.designcolorpalette.ui.screen.saved

import androidx.lifecycle.viewModelScope
import com.honey.designcolorpalette.base.BaseViewModel
import com.honey.designcolorpalette.ui.screen.saved.contact.SavedEffect
import com.honey.designcolorpalette.ui.screen.saved.contact.SavedEvent
import com.honey.designcolorpalette.ui.screen.saved.contact.SavedState
import com.honey.designcolorpalette.ui.screen.saved.model.SavedTabs
import com.honey.domain.model.ColorSchemeFilters
import com.honey.domain.model.SavedColorScheme
import com.honey.domain.usecase.FilterColorSchemeUseCase
import com.honey.domain.usecase.GetAllColorSchemeUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class SavedViewModel(
    private val getAllSavedColorScheme: GetAllColorSchemeUseCase,
    private val filterColorScheme: FilterColorSchemeUseCase
) : BaseViewModel<SavedEvent, SavedState, SavedEffect>(initialState = SavedState.Loading) {

    private val allColorSchemesState = MutableStateFlow<List<SavedColorScheme>?>(null)

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

    private fun performDeleteColor(colorScheme: SavedColorScheme) {
        // TODO Send DeleteRequest to Database
        loadColorSchemes()
    }

    private fun performOpenScheme(state: SavedState.Show, colorScheme: SavedColorScheme? = null ) {
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
                            val filtered = filterColorScheme.invoke(allColorSchemes, ColorSchemeFilters.SingleColor)
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