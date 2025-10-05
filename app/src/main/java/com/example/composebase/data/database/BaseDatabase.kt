package com.example.composebase.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.composebase.data.database.entity.TestEntity

@Database(
    entities = [TestEntity::class],
    version = 1,
    exportSchema = true
)
abstract class BaseDatabase : RoomDatabase() {
    //TODO Add your DAOs here
}