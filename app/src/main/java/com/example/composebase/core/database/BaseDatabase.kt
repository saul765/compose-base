package com.example.composebase.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.composebase.core.database.dao.IPokemonDao
import com.example.composebase.core.database.entity.PokemonEntity

@Database(
    entities = [PokemonEntity::class],
    version = 1,
    exportSchema = true
)
abstract class BaseDatabase : RoomDatabase() {
    abstract fun pokemonDao(): IPokemonDao
}