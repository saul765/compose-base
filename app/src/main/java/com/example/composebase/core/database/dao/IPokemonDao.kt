package com.example.composebase.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.composebase.core.database.entity.PokemonEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface IPokemonDao {

    @Insert
    suspend fun saveAll(entities: List<PokemonEntity>)

    @Query("SELECT * FROM pokemons WHERE id = :id")
    suspend fun findPokemonById(id: Int): PokemonEntity

    @Query("SELECT * FROM pokemons ORDER BY id ASC")
    fun findAll(): Flow<List<PokemonEntity>>

    @Query("SELECT COUNT(*) FROM pokemons")
    suspend fun getTotalNumberOfPokemons(): Int


}