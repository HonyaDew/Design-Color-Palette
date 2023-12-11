package com.honyadew

import kotlinx.coroutines.flow.MutableStateFlow

object GlobalSignals{
    val showSettingsState = MutableStateFlow(false)
    val snackbarHostState = MutableStateFlow("")
}
