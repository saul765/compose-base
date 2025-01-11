package com.example.composebase.core.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.composebase.core.ZERO_INTEGER


@Entity(tableName = "test_table")
data class TestEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = ZERO_INTEGER
)