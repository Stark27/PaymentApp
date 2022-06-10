package com.example.luismunoz.paymentapp.domain.model.summary

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataSummary(
    val amountValue: String,
    val paymentMethodName: String,
    val bankName: String,
    val feeNumber: Int,
    val feeAmount: Double,
    val totalAmount: Double,
    val transactionId: String
) : Parcelable
