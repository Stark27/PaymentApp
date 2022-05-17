package com.example.luismunoz.paymentapp.data.source.remote

import com.example.luismunoz.paymentapp.data.exception.ServiceException
import com.example.luismunoz.paymentapp.data.source.remote.response.bank.BankResponse
import com.example.luismunoz.paymentapp.data.source.remote.retrofit.BankService
import com.example.luismunoz.paymentapp.domain.Resource
import com.example.luismunoz.paymentapp.domain.repository.BankSelectionRepository
import javax.inject.Inject

/**
 *  Repository that get all banks from remote by payment methodId
 */
class BankSelectionRepositoryImpl @Inject constructor(private val bankService: BankService) : BankSelectionRepository {

    /**
     *  Get all banks from remote and returns a Resource<List<BankResponse>?> response
     */
    override suspend fun getBanksFromRemote(paymentMethodId: String): Resource<List<BankResponse>?> {
        return try {
            val call = bankService.getBanks(paymentMethodId = paymentMethodId)

            if (call.isSuccessful) {
                Resource.Success(call.body())
            } else {
                Resource.Error(ServiceException())
            }
        } catch (e: Exception) {
            Resource.Error(e)
        }
    }

}