package com.example.luismunoz.paymentapp.domain.usecase

import com.example.luismunoz.paymentapp.data.exception.ServiceException
import com.example.luismunoz.paymentapp.data.source.remote.BankSelectionRepositoryImpl
import com.example.luismunoz.paymentapp.data.source.remote.response.bank.BankResponse
import com.example.luismunoz.paymentapp.domain.Resource
import com.example.luismunoz.paymentapp.domain.model.DataBank
import com.example.luismunoz.paymentapp.util.BANK_ACTIVE
import javax.inject.Inject

/**
 *  UseCase that handle data from remote repository with all banks
 */
class GetAllBanksByPaymentMethodIdUseCase @Inject constructor(private val repository: BankSelectionRepositoryImpl) {

    /**
     *  Get all banks from remote, filter that data and returns
     *  a Resource<List<DataBank>>
     *
     *  [paymentMethodId] Identifier to get data for that payment method
     *
     */
    suspend fun getAllBanksByPaymentMethodId(paymentMethodId: String): Resource<MutableList<DataBank>> {
        return when(val response = repository.getBanksFromRemote(paymentMethodId)) {
            is Resource.Success -> {
                if (response.data != null) {
                    val banks = processRemoteData(response.data)
                    Resource.Success(banks)
                } else {
                    Resource.Error(ServiceException())
                }
            }
            is Resource.Error -> {
                Resource.Error(response.exception)
            }
            else -> {
                Resource.Error(ServiceException())
            }
        }
    }

    /**
     *  Receive all banks from remote repository and returns a MutableList<DataBank>
     *
     *  [remoteBanks] list with all banks
     */
    private fun processRemoteData(remoteBanks: List<BankResponse>): MutableList<DataBank> {
        val banks = mutableListOf<DataBank>()

        remoteBanks.forEach { bank ->
            if (bank.status.equals(BANK_ACTIVE)
                && !bank.id.isNullOrEmpty()
                && !bank.name.isNullOrEmpty()
                && !bank.secureThumbnail.isNullOrEmpty()
            ) {

                banks.add(
                    DataBank(
                        bankId = bank.id,
                        bankName = bank.name,
                        bankPath = bank.secureThumbnail
                    )
                )
            }
        }

        return banks
    }

}