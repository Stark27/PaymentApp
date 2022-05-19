package com.example.luismunoz.paymentapp.domain.repository

import com.example.luismunoz.paymentapp.data.source.remote.response.fee.FeeResponse
import com.example.luismunoz.paymentapp.domain.Resource

interface FeeSelectionRepository {

    suspend fun getAvailableFeeFromRemote(amountValue: String, paymentMethodId: String, issuerId: String): Resource<List<FeeResponse>?>

}