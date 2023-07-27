package com.honey.domain.usecase

import com.honyadew.model.ColorInfo

class AddColorToListUseCase {
    fun invoke(list: List<ColorInfo>, color: ColorInfo) : List<ColorInfo>{
        val arrayList = ArrayList(list)
        arrayList.add(color)
        return arrayList.toList()
    }
}