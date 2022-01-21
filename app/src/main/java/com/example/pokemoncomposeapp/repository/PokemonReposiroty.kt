package com.example.pokemoncomposeapp.repository

import com.example.pokemoncomposeapp.data.PokemonApi
import com.example.pokemoncomposeapp.responses.Pokemon
import com.example.pokemoncomposeapp.responses.PokemonList
import com.example.pokemoncomposeapp.util.Resource
import javax.inject.Inject

class PokemonReposiroty @Inject constructor(
  val api : PokemonApi
) {

    suspend fun getPokemonList(limit : Int , offset: Int) : Resource<PokemonList>{
       val result = try{
           api.getPokemonList(limit,offset)
       }catch (ex : Exception){
           return Resource.ERROR("An unknown error occured")
       }
        return Resource.SUCCESS(result)
    }

    suspend fun getPokemon(name : String) : Resource<Pokemon>{
        val result = try{
            api.getPokemon(name)
        }catch (ex : Exception){
            return Resource.ERROR("An unknown error occured")
        }
        return Resource.SUCCESS(result)
    }
}