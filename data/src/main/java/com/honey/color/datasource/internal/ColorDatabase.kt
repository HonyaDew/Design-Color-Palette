package com.honey.color.datasource.internal

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.honey.color.datasource.model.ColorInfoData

@Database(entities = [ColorInfoData::class], version = 1)
abstract class ColorDatabase : RoomDatabase(){
    abstract fun colorDao() : ColorDao
}