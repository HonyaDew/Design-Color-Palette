package com.honyadew.model

import kotlinx.serialization.Serializable

@Serializable
data class CustomColorScheme(
    val colors: List<ColorInfo>,
    val name: String,
    val source: ColorSchemeSource = ColorSchemeSource.Created
)

@Serializable
sealed class ColorSchemeSource{
    @Serializable object Created : ColorSchemeSource()
    @Serializable object FromHarmony : ColorSchemeSource()
    @Serializable object BasicAsset : ColorSchemeSource()
    @Serializable object ExtractAuto : ColorSchemeSource()
    @Serializable object ExtractManual : ColorSchemeSource()
}

sealed class ColorSchemeFilters{
    object SingleColor : ColorSchemeFilters()
    object UpToFourColors : ColorSchemeFilters()
    object MultiColors: ColorSchemeFilters()
}