package com.honey.saved.datasource.internal

import androidx.room.Database
import androidx.room.RoomDatabase
import com.honey.saved.model.ColorInfoData
import com.honey.saved.model.CustomPaletteInfoData

@Database(entities = [ColorInfoData::class, CustomPaletteInfoData::class], version = 1)
abstract class SavedDatabase : RoomDatabase(){
    abstract fun savedDao() : SavedDao
}