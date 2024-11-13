package com.example.ecodicas

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [DicaModel::class], version = 1)
abstract class DicaDatabase : RoomDatabase() {
    abstract fun dicaDao(): DicaDao
}