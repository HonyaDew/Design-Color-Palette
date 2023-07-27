package com.honey.domain.usecase

import com.honyadew.model.ColorSchemeFilters
import com.honyadew.model.CustomColorScheme

class FilterColorSchemeUseCase {
    fun invoke(colorSchemes : List<CustomColorScheme>, filter: ColorSchemeFilters): List<CustomColorScheme> {
        return when (filter){
            ColorSchemeFilters.SingleColor -> {
                colorSchemes.filter { colorScheme ->
                    colorScheme.colors.size == 1
                }
            }
            ColorSchemeFilters.UpToFourColors -> {
                colorSchemes.filter { colorScheme ->
                    colorScheme.colors.size in 2..4
                }
            }
            ColorSchemeFilters.MultiColors -> {
                colorSchemes.filter { colorScheme ->
                    colorScheme.colors.size >=5
                }
            }
        }
    }
}