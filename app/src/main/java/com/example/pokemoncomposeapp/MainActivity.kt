package com.example.pokemoncomposeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavArgument
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.pokemoncomposeapp.representation.PokemonHome.PokemonHome
import com.example.pokemoncomposeapp.ui.theme.PokemonComposeAppTheme
import com.example.pokemoncomposeapp.util.DOMINANT_COLOR_KEY
import com.example.pokemoncomposeapp.util.POKEMON_DETAIL_SCREEN
import com.example.pokemoncomposeapp.util.POKEMON_HOME_SCREEN
import com.example.pokemoncomposeapp.util.POKEMON_NAME_KEY

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokemonComposeAppTheme {
                val navController =  rememberNavController()
                NavHost(navController = navController,startDestination = POKEMON_HOME_SCREEN){
                    composable(POKEMON_HOME_SCREEN){
                        PokemonHome(navController = navController)
                    }

                    composable("$POKEMON_DETAIL_SCREEN/${DOMINANT_COLOR_KEY}/${POKEMON_NAME_KEY}",
                    arguments = listOf(
                        navArgument(DOMINANT_COLOR_KEY){
                            type = NavType.IntType
                        },
                        navArgument(POKEMON_NAME_KEY){
                            type = NavType.StringType
                        }
                    )
                    ){
                        val dominantColor = remember{
                           val color = it.arguments?.getInt(DOMINANT_COLOR_KEY)
                            color?.let { Color(it)} ?: Color.White
                        }
                        val pokemonName = remember{
                            it.arguments?.getString(POKEMON_NAME_KEY)
                        }
                    }
                }
            }
        }
    }
}

