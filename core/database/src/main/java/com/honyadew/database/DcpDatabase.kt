package com.honyadew.database

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.honyadew.database.dao.SavedDao
import com.honyadew.database.model.ColorInfoEntity
import com.honyadew.database.model.CustomColorSchemeEntity
import com.honyadew.database.unil.ColorInfoConverter
import com.honyadew.database.unil.PaletteTypeConverter
import com.honyadew.database.unil.SourceConverter

@Database(
    entities = [
        ColorInfoEntity::class,
        CustomColorSchemeEntity::class
    ],
    version = 2,
//    autoMigrations = [
//        AutoMigration(from = 1, to = 2)
//    ],
    exportSchema = true
)
@TypeConverters(
    PaletteTypeConverter::class,
    ColorInfoConverter::class,
    SourceConverter::class
)
abstract class DcpDatabase : RoomDatabase(){
    abstract fun savedDao() : SavedDao
}