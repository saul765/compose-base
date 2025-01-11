package com.example.composebase.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.composebase.core.model.entity.TestEntity

@Database(
    entities = [TestEntity::class],
    version = 1,
    exportSchema = true
)
abstract class BaseDatabase : RoomDatabase() {
    //TODO Add your DAOs here
}