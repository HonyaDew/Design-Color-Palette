package com.honey.domain.usecase

import com.honey.domain.model.ColorInfo
import com.honey.domain.model.ColorSchemeFilters
import com.honey.domain.model.SavedColorScheme

class FilterColorSchemeUseCase {
    fun invoke(colorSchemes : List<SavedColorScheme>, filter: ColorSchemeFilters): List<SavedColorScheme> {
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