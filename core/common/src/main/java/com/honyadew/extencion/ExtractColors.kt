package com.honyadew.extencion

import android.graphics.Bitmap
import androidx.compose.ui.graphics.Color
import androidx.palette.graphics.Palette
import com.honyadew.common.R
import com.honyadew.model.ColorInfo

fun Bitmap.extractColors() : List<ColorInfo> {
    val arrayList = arrayListOf<ColorInfo>()
    Palette.from(this).generate().vibrantSwatch?.rgb?.let { intColor ->
        val color = Color(intColor)
        arrayList.add(
            ColorInfo(
                value = color.string(),
                name = "Vibrant"
            )
        )
    }
    Palette.from(this).generate().darkVibrantSwatch?.rgb?.let { intColor ->
        val color = Color(intColor)
        arrayList.add(
            ColorInfo(
                value = color.string(),
                name = "Dark vibrant"
            )

        )
    }
    Palette.from(this).generate().darkVibrantSwatch?.bodyTextColor?.let { intColor ->
        val color = Color(intColor)
        arrayList.add(
            ColorInfo(
                value = color.string(),
                name = "On dark vibrant"
            )

        )
    }
    Palette.from(this).generate().lightVibrantSwatch?.rgb?.let { intColor ->
        val color = Color(intColor)
        arrayList.add(
            ColorInfo(
                value = color.string(),
                name = "Light vibrant"
            )
        )
    }
    Palette.from(this).generate().dominantSwatch?.rgb?.let { intColor ->
        val color = Color(intColor)
        arrayList.add(
            ColorInfo(
                value = color.string(),
                name = "Domain vibrant"
            )

        )
    }
    Palette.from(this).generate().mutedSwatch?.rgb?.let { intColor ->
        val color = Color(intColor)
        arrayList.add(
            ColorInfo(
                value = color.string(),
                name = "Muted swatch"
            )
        )
    }
    Palette.from(this).generate().lightMutedSwatch?.rgb?.let { intColor ->
        val color = Color(intColor)
        arrayList.add(
            ColorInfo(
                value = color.string(),
                name = "Light muted"
            )
        )
    }
    Palette.from(this).generate().darkMutedSwatch?.rgb?.let { intColor ->
        val color = Color(intColor)
        arrayList.add(
            ColorInfo(
                value = color.string(),
                name = "Dark muted"
            )
        )
    }
    return arrayList.toList()
}