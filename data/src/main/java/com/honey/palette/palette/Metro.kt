package com.honey.palette.palette

import androidx.compose.ui.graphics.Color
import com.honey.domain.model.ColorInfo
import com.honey.domain.model.Palette
import com.honey.palette.extencion.string

object Metro {
    val palette = listOf<ColorInfo>(
        ColorInfo(value = Color(0xFFA4C400).string(), name = "Lime", palette = Palette.Metro),
        ColorInfo(value = Color(0xFF60A917).string(), name = "Green", palette = Palette.Metro),
        ColorInfo(value = Color(0xFF008A00).string(), name = "Emerald", palette = Palette.Metro),
        ColorInfo(value = Color(0xFF00ABA9).string(), name = "Teal", palette = Palette.Metro),
        ColorInfo(value = Color(0xFF1BA1E2).string(), name = "Cyan", palette = Palette.Metro),
        ColorInfo(value = Color(0xFF0050EF).string(), name = "Cobalt", palette = Palette.Metro),
        ColorInfo(value = Color(0xFF6A00FF).string(), name = "Indigo", palette = Palette.Metro),
        ColorInfo(value = Color(0xFFAA00FF).string(), name = "Violet", palette = Palette.Metro),
        ColorInfo(value = Color(0xFFF472D0).string(), name = "Pink", palette = Palette.Metro),
        ColorInfo(value = Color(0xFFD80073).string(), name = "Magenta", palette = Palette.Metro),
        ColorInfo(value = Color(0xFFA20025).string(), name = "Crimson", palette = Palette.Metro),
        ColorInfo(value = Color(0xFFE51400).string(), name = "Red", palette = Palette.Metro),
        ColorInfo(value = Color(0xFFFA6800).string(), name = "Orange", palette = Palette.Metro),
        ColorInfo(value = Color(0xFFF0A30A).string(), name = "Amber", palette = Palette.Metro),
        ColorInfo(value = Color(0xFFE3C800).string(), name = "Yellow", palette = Palette.Metro),
        ColorInfo(value = Color(0xFF825A2C).string(), name = "Brown", palette = Palette.Metro),
        ColorInfo(value = Color(0xFF6D8764).string(), name = "Olive", palette = Palette.Metro),
        ColorInfo(value = Color(0xFF647687).string(), name = "Steel", palette = Palette.Metro),
        ColorInfo(value = Color(0xFF76608A).string(), name = "Mauve", palette = Palette.Metro),
        ColorInfo(value = Color(0xFFA0522D).string(), name = "Sienna", palette = Palette.Metro)
    )
}