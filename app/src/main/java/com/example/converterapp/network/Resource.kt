package com.example.converterapp.network

/*sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
    class Loading<T>(data: T? = null) : Resource<T>(data)
   // data class Loading<out T>(val message: String): ApiResponseV2<T>()
}*/

sealed class Resource<out T> {
    data class Success<out T>(val data: T?): Resource<T>()
    //data class Loading<out T>(val message: String = "Loading..."): Resource<T>()
    data class Loading <out T>(val message: String = "Loading...", val data: T?= null): Resource<T>()
    data class Error<out T>(val message: String?): Resource<T>()
}