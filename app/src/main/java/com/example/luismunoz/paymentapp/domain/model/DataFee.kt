package com.example.luismunoz.paymentapp.domain.model

data class DataFee(
    val numberFee: Int,
    val message: String,
    val paymentMethodOptionId: String,
    val totalAmount: Double,
    val feeAmount: Double
) {
    override fun toString(): String {
        return numberFee.toString()
    }
}
