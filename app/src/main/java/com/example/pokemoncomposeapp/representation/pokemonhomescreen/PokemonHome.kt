package com.example.pokemoncomposeapp.representation.pokemonhomescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.request.ImageRequest
import com.example.pokemoncomposeapp.R
import com.example.pokemoncomposeapp.data.PokemonHomeEntry
import com.example.pokemoncomposeapp.responses.PokemonList
import com.example.pokemoncomposeapp.ui.theme.RobotoCondensed
import com.example.pokemoncomposeapp.util.POKEMON_DETAIL_SCREEN
import com.google.accompanist.coil.CoilImage

@Composable
fun PokemonHome(
    navController: NavController,
    pokemonHomeViewModel : PokemonHomeViewModel = hiltViewModel()
) {
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column {
            Spacer(modifier = Modifier.height(20.dp))
            Image(
                painter = painterResource(id = R.drawable.ic_international_pok_mon_logo),
                contentDescription = "pokemon_logo",
                modifier = Modifier
                    .fillMaxWidth()
                    .align(CenterHorizontally)
            )
            SearchBar(
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                    newSearchTxt -> pokemonHomeViewModel.searchPokemonList(newSearchTxt)
            }
            Spacer(modifier = Modifier.height(16.dp))
            PokemonHome(navController = navController)
            }
        }
    }

@Composable
fun SearchBar(
    modifier: Modifier,
    onSearch: (String) -> Unit
) {
    var text by remember { mutableStateOf("") }
    Box(modifier = modifier) {
        TextField(
            value = text,
            onValueChange = { newSearchTxt ->
                text = newSearchTxt
                onSearch(newSearchTxt)
            },
            placeholder = { Text("Search") },
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(color = Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 12.dp)
                .shadow(elevation = 5.dp, shape = CircleShape)
                .background(color = Color.White, shape = CircleShape),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search,
                keyboardType = KeyboardType.Text
            ),
            keyboardActions = KeyboardActions(
                onSearch = { onSearch(text) }
            )
        )
    }
}

@Composable
fun PokemonEntry(
    entry: PokemonHomeEntry,
    navController: NavController,
    modifier: Modifier,
    pokemonHomeViewModel: PokemonHomeViewModel = hiltViewModel()
) {
    val defaultColor = MaterialTheme.colors.surface
    var dominantColor by remember {
        mutableStateOf(defaultColor)
    }
    Box(
        contentAlignment = Center,
        modifier = modifier
            .aspectRatio(1f)
            .shadow(5.dp, RoundedCornerShape(10.dp))
            .clip(RoundedCornerShape(10.dp))
            .background(
                Brush.verticalGradient(
                    listOf(
                        dominantColor,
                        defaultColor
                    )
                )
            )
            .clickable {
                navController.navigate("$POKEMON_DETAIL_SCREEN/$dominantColor/${entry.name}")
            }
    ) {
        Column {
            CoilImage(
                request = ImageRequest.Builder(LocalContext.current).data(
                    entry.url
                ).target { resultDrawable ->
                    pokemonHomeViewModel.calcDominantColor(resultDrawable) {
                        dominantColor = it
                    }
                }.build(),
                contentDescription = entry.name,
                fadeIn = true,
                modifier = Modifier
                    .size(120.dp)
                    .align(CenterHorizontally)
            ) {
                CircularProgressIndicator(
                    color = MaterialTheme.colors.primary,
                    modifier = Modifier.scale(0.5f)
                )

            }
            Text(
                text = entry.name,
                fontSize = 20.sp,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontFamily = RobotoCondensed

            )
        }
    }
}

@Composable
fun PokemonRow(
    rowIndex: Int,
    navController: NavController,
    homeEntries: List<PokemonHomeEntry>
) {
    Row {
        PokemonEntry(
            entry = homeEntries[rowIndex * 2],
            modifier = Modifier.weight(1f),
            navController = navController
        )
        Spacer(modifier = Modifier.width(16.dp))
        if (homeEntries.size >= rowIndex * 2 + 2) {
            PokemonEntry(
                entry = homeEntries[rowIndex * 2 + 1],
                modifier = Modifier.weight(1f),
                navController = navController
            )
        } else {
            Spacer(modifier = Modifier.weight(1f))
        }
    }
    Spacer(modifier = Modifier.height(20.dp))
}