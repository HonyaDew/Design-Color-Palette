package com.honey.saved.datasource.internal

import androidx.room.Database
import androidx.room.RoomDatabase
import com.honey.saved.model.ColorInfoData
import com.honey.saved.model.CustomColorSchemeData

@Database(entities = [ColorInfoData::class, CustomColorSchemeData::class], version = 1)
abstract class SavedDatabase : RoomDatabase(){
    abstract fun savedDao() : SavedDao
}