package com.example.composebase.core.webservice.apis

import com.example.composebase.core.webservice.dto.responses.PokemonItemDTO
import com.example.composebase.core.webservice.dto.responses.PokemonResultDTO
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface IPokemonApi {

    companion object {
        //Params
        const val LIMIT_PARAM = "limit"
        const val OFFSET_PARAM = "offset"

        //Endpoints
        const val GET_POKEMONS = "pokemon"

    }

    @GET(GET_POKEMONS)
    suspend fun getPokemons(
        @Query(LIMIT_PARAM) limit: Int,
        @Query(OFFSET_PARAM) offset: Int
    ): PokemonResultDTO

    @GET
    suspend fun getPokemonDetail(@Url pokemonUrl: String): PokemonItemDTO
}