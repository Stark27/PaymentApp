package com.example.luismunoz.paymentapp.domain

sealed class Resource<out R> {

    object Loading: Resource<Nothing>()
    data class Success<out T>(val data: T) : Resource<T>()
    data class Error(val exception: Exception) : Resource<Nothing>()

}