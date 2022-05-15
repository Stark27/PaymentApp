package com.example.luismunoz.paymentapp.viewmodel

class Event<out T>(private val content: T) {

    private var hasBeenHandled = false

    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled)
            null
        else {
            hasBeenHandled = true
            content
        }
    }
}