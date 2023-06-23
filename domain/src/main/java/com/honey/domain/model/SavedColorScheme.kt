package com.honey.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class SavedColorScheme(
    val colors: List<ColorInfo>,
    val name: String,
    val source: CustomPaletteSources = CustomPaletteSources.Created
)

@Serializable
sealed class CustomPaletteSources{
    @Serializable object Created : CustomPaletteSources()
    @Serializable object FromHarmony : CustomPaletteSources()
    @Serializable object BasicAsset : CustomPaletteSources()
}

sealed class ColorSchemeFilters{
    object SingleColor : ColorSchemeFilters()
    object UpToFourColors : ColorSchemeFilters()
    object MultiColors: ColorSchemeFilters()
}