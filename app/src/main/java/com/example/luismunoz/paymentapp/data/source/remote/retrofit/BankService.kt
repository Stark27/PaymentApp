package com.example.luismunoz.paymentapp.data.source.remote.retrofit

import com.example.luismunoz.paymentapp.data.source.remote.response.bank.BankResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BankService {

    @GET("payment_methods/card_issuers")
    suspend fun getBanks(@Query("payment_method_id") paymentMethodId: String): Response<List<BankResponse>>
}