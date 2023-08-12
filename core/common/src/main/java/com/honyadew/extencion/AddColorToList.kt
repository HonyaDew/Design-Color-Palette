package com.honyadew.extencion

import com.honyadew.model.ColorInfo

fun List<ColorInfo>.addColor(color: ColorInfo) : List<ColorInfo>{
    val arrayList = ArrayList(this)
    arrayList.add(color)
    return arrayList.toList()
}