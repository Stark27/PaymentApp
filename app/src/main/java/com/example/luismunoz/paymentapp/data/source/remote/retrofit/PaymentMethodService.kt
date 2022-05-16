package com.example.luismunoz.paymentapp.data.source.remote.retrofit

import com.example.luismunoz.paymentapp.data.source.remote.response.paymentMethod.PaymentMethodResponse
import retrofit2.Response
import retrofit2.http.GET

interface PaymentMethodService {

    @GET("payment_methods")
    suspend fun getPaymentMethod(): Response<List<PaymentMethodResponse>>
}