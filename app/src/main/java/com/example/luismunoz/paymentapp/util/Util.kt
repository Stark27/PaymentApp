package com.example.luismunoz.paymentapp.util

class Util {

    companion object {

        fun isNumberStartWithZero(amountValue: String): Boolean {
            val regexValidation = "^[0](.*)".toRegex()
            return amountValue.matches(regexValidation)
        }

    }
}