package com.example.pokemoncomposeapp.util

sealed class Resource<T>(val data: T? = null,val message: String? = null) {
    class LOADING<T>() : Resource<T>()
    class SUCCESS<T>(data: T?) : Resource<T>(data)
    class ERROR<T>(message: String?, data: T? = null) : Resource<T>(message = message, data = data)
}
