package com.example.pokemoncomposeapp.representation.pokemondetailscreen

import androidx.lifecycle.ViewModel
import com.example.pokemoncomposeapp.repository.PokemonReposiroty
import com.example.pokemoncomposeapp.responses.Pokemon
import com.example.pokemoncomposeapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val pokemonRepo : PokemonReposiroty
) : ViewModel() {
    suspend fun getPokemonInfo(pokemonName : String):Resource<Pokemon>{
       return pokemonRepo.getPokemon(pokemonName)
    }
}