package com.example.composebase.di

import androidx.room.Room
import com.example.composebase.core.DATABASE_NAME
import com.example.composebase.core.database.BaseDatabase
import org.koin.dsl.module

object DatabaseModule {

    val module = module {

        single<BaseDatabase> {
            Room.databaseBuilder(get(), BaseDatabase::class.java, DATABASE_NAME).build()
        }

    }
}