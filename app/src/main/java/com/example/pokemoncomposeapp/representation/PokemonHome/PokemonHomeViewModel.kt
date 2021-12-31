package com.example.pokemoncomposeapp.representation.PokemonHome

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmapConfig
import androidx.compose.ui.graphics.ImageBitmapConfig.Companion.Argb8888
import androidx.lifecycle.ViewModel
import androidx.palette.graphics.Palette
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokemonHomeViewModel @Inject constructor() : ViewModel() {
    fun calcDominantColor(drawable : Drawable, onFinish : (Color) -> Unit){
        val drawableAsBitmap = (drawable as BitmapDrawable)
            .bitmap
            .copy(Bitmap.Config.ARGB_8888,true)
    Palette.from(drawableAsBitmap).generate(){
        palette -> palette?.dominantSwatch?.rgb?.let{
            colorValue -> onFinish(Color(colorValue))
    }
    }
    }

    fun loadPaginatedPokeList(){
        
    }
}