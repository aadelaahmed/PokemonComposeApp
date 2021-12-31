package com.example.pokemoncomposeapp.util

sealed class Resource<T>(data: T?, message: String? = null) {
    class LOADING<T>(data: T? = null) : Resource<T>(data)
    class SUCCESS<T>(data: T?) : Resource<T>(data)
    class ERROR<T>(message: String?, data: T? = null) : Resource<T>(message = message, data = data)
}
