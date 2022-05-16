package com.example.luismunoz.paymentapp.domain.repository

import com.example.luismunoz.paymentapp.data.source.remote.response.paymentMethod.PaymentMethodResponse
import com.example.luismunoz.paymentapp.domain.Resource

interface PaymentSelectionRepository {

    suspend fun getPaymentMethodsFromRemote(): Resource<List<PaymentMethodResponse>?>
}