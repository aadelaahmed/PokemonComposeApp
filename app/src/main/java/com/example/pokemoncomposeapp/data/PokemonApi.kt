package com.example.pokemoncomposeapp.data

import com.example.pokemoncomposeapp.responses.Pokemon
import com.example.pokemoncomposeapp.responses.PokemonList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApi {
    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit : Int,
        @Query("offset") offset : Int
    ) : PokemonList

    @GET("pokemon/{id}")
    suspend fun getPokemon(
        @Path("id") id : Int
    ) : Pokemon
}