package com.example.luismunoz.paymentapp.domain.repository

import com.example.luismunoz.paymentapp.data.source.remote.response.bank.BankResponse
import com.example.luismunoz.paymentapp.domain.Resource

interface BankSelectionRepository {

    suspend fun getBanksFromRemote(paymentMethodId: String): Resource<List<BankResponse>?>
}