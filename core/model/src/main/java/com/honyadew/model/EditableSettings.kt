package com.honyadew.model

import kotlinx.serialization.Serializable

@Serializable
data class EditableSettings(
    val palette: Palette,
    val themeConfig: ThemeConfig
)