package com.honey.domain.usecase

import com.honey.domain.model.ColorSchemeFilters
import com.honey.domain.model.CustomColorScheme

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