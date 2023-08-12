package com.honyadew.extencion

import com.honyadew.model.ColorInfo

fun List<ColorInfo>.removeColor(color: ColorInfo) : List<ColorInfo>{
    val arrayList = ArrayList(this)
    arrayList.remove(color)
    return arrayList.toList()
}