package com.example.pokemoncomposeapp.representation.pokemondetailscreen

import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.pokemoncomposeapp.responses.Pokemon
import com.example.pokemoncomposeapp.util.Resource
import com.google.accompanist.coil.CoilImage

@Composable
fun PokemonDetailScreen(
    dominantColor : Color,
    pokemonName : String,
    topPadding : Dp = 16.dp,
    imagePokemonSize : Dp = 200.dp,
    navController : NavController,
    pokemonDetailViewModel: PokemonDetailViewModel = hiltViewModel()
) {
    val pokemonInfo = produceState<Resource<Pokemon>>(initialValue =Resource.LOADING()){
        value =  pokemonDetailViewModel.getPokemonInfo(pokemonName)
    }.value
    Box(modifier = Modifier
        .fillMaxSize()
        .background(dominantColor)
        .padding(bottom = 16.dp)
    ){
        PokemonDetailTopSection(navController = navController,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.25f)
            .align(Alignment.TopCenter)
        )
        PokemonDetailWrapper(
            pokemonInfo = pokemonInfo,
            navController = navController,
            modifierContent = Modifier
                .padding(
                    top = topPadding + imagePokemonSize / 2f,
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 16.dp
                )
                .fillMaxSize()
                .fillMaxWidth()
                .background(MaterialTheme.colors.surface)
                .shadow(4.dp, RoundedCornerShape(10.dp))
                .clip(RoundedCornerShape(10.dp))
//                .padding(16.dp)
                .align(Alignment.Center)
            ,
            modifierLoadingBar = Modifier
                .align(Alignment.Center)
                .size(100.dp)
                .padding(
                    top = topPadding + imagePokemonSize / 2f,
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 16.dp
                )
        )

        Box(modifier = Modifier
            .fillMaxSize(),
            contentAlignment = Alignment.TopCenter
        ){
            pokemonInfo.data?.sprites.let {
                CoilImage(data = it!!.backDefault,
                    contentDescription = pokemonName,
                    fadeIn = true,
                    modifier = Modifier.offset(y = 16.dp)
                        .size(imagePokemonSize)
                    )
            }
        }
    }
}

@Composable
fun PokemonDetailWrapper(
    pokemonInfo : Resource<Pokemon>,
    navController: NavController,
    modifierContent : Modifier,
    modifierLoadingBar : Modifier
) {
    when(pokemonInfo){
        is Resource.SUCCESS -> {

        }
        is Resource.LOADING ->{
                CircularProgressIndicator(
                    color = MaterialTheme.colors.primary,
                    modifier = modifierLoadingBar
                )
        }
        is Resource.ERROR -> {
            Text(
                pokemonInfo.message!!,
                color = Color.Red,
                modifier = modifierContent
            )
        }
    }
}

@Composable
fun PokemonDetailTopSection(
    modifier : Modifier,
    navController: NavController
) {
    Box(contentAlignment = Alignment.TopStart,
        modifier = Modifier
        .background(Brush.verticalGradient(
            listOf(
                Color.Black,
                Color.Transparent
            )
        ))
    ){
        Icon(imageVector = Icons.Default.ArrowBack,
            contentDescription = null,
            tint = Color.White
        ,modifier = Modifier
                .offset(16.dp,16.dp)
                .clickable { navController.popBackStack() }
                .size(36.dp)
        )
    }
}