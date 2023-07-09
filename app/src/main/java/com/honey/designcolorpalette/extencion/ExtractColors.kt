package com.honey.designcolorpalette.extencion

import android.graphics.Bitmap
import androidx.compose.ui.graphics.Color
import androidx.palette.graphics.Palette
import com.honey.designcolorpalette.R
import com.honey.domain.model.ExtractColor

fun Bitmap.extractColors() : List<ExtractColor> {
    val arrayList = arrayListOf<ExtractColor>()
    Palette.from(this).generate().vibrantSwatch?.rgb?.let { intColor ->
        val color = Color(intColor)
        arrayList.add(
            ExtractColor(
                stringId = R.string.vibrant,
                color = color.string()
            )
        )
    }
    Palette.from(this).generate().darkVibrantSwatch?.rgb?.let { intColor ->
        val color = Color(intColor)
        arrayList.add(
            ExtractColor(
            stringId = R.string.dark_vibrant,
            color = color.string()
        )
        )
    }
    Palette.from(this).generate().darkVibrantSwatch?.bodyTextColor?.let { intColor ->
        val color = Color(intColor)
        arrayList.add(
            ExtractColor(
            stringId = R.string.on_dark_vibrant,
            color = color.string()
        )
        )
    }
    Palette.from(this).generate().lightVibrantSwatch?.rgb?.let { intColor ->
        val color = Color(intColor)
        arrayList.add(
            ExtractColor(
            stringId = R.string.light_vibrant,
            color = color.string()
        )
        )
    }
    Palette.from(this).generate().dominantSwatch?.rgb?.let { intColor ->
        val color = Color(intColor)
        arrayList.add(
            ExtractColor(
            stringId = R.string.domain_vibrant,
            color = color.string()
        )
        )
    }
    Palette.from(this).generate().mutedSwatch?.rgb?.let { intColor ->
        val color = Color(intColor)
        arrayList.add(
            ExtractColor(
            stringId = R.string.muted_swatch,
            color = color.string()
        )
        )
    }
    Palette.from(this).generate().lightMutedSwatch?.rgb?.let { intColor ->
        val color = Color(intColor)
        arrayList.add(
            ExtractColor(
            stringId = R.string.light_muted,
            color = color.string()
        )
        )
    }
    Palette.from(this).generate().darkMutedSwatch?.rgb?.let { intColor ->
        val color = Color(intColor)
        arrayList.add(
            ExtractColor(
            stringId = R.string.dark_muted,
            color = color.string()
        )
        )
    }
    return arrayList.toList()
}