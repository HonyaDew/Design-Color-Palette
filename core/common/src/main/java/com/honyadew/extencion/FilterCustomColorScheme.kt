package com.honyadew.extencion

import com.honyadew.model.ColorSchemeFilters
import com.honyadew.model.CustomColorScheme

fun List<CustomColorScheme>.filterApply(filter: ColorSchemeFilters): List<CustomColorScheme> {
    return when (filter){
        ColorSchemeFilters.SingleColor -> {
            this.filter { colorScheme ->
                colorScheme.colors.size == 1
            }
        }
        ColorSchemeFilters.UpToFourColors -> {
            this.filter { colorScheme ->
                colorScheme.colors.size in 2..4
            }
        }
        ColorSchemeFilters.MultiColors -> {
            this.filter { colorScheme ->
                colorScheme.colors.size >=5
            }
        }
    }
}