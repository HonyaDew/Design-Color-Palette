package com.honey.domain.usecase

import com.honey.domain.model.ColorInfo

class RemoveColorFromListUseCase {
    fun invoke(list: List<ColorInfo>, color: ColorInfo) : List<ColorInfo>{
        val arrayList = ArrayList(list)
        arrayList.remove(color)
        return arrayList.toList()
    }
}