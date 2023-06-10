package com.honey.domain.model

import com.honey.domain.model.Palette
import kotlinx.serialization.Serializable

@Serializable
data class EditableSettings(
    val palette: Palette
)