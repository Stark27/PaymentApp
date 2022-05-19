package com.example.luismunoz.paymentapp.data.source.remote.retrofit

import com.example.luismunoz.paymentapp.data.source.remote.response.fee.FeeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FeeService {

    @GET("payment_methods/installments")
    suspend fun getAvailableFee(
        @Query("amount") amountValue: String,
        @Query("payment_method_id") paymentMethodId: String,
        @Query("issuer.id") issuerId: String
    ): Response<List<FeeResponse>>

}