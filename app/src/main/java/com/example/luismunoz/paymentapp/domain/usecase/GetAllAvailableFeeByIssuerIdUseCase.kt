package com.example.luismunoz.paymentapp.domain.usecase

import com.example.luismunoz.paymentapp.data.exception.ServiceException
import com.example.luismunoz.paymentapp.data.source.remote.FeeSelectionRepositoryImpl
import com.example.luismunoz.paymentapp.data.source.remote.response.fee.FeeResponse
import com.example.luismunoz.paymentapp.domain.Resource
import com.example.luismunoz.paymentapp.domain.model.fee.DataFee
import com.example.luismunoz.paymentapp.domain.model.fee.fromDomainToUi
import javax.inject.Inject

/**
 *  UseCase that handle data from remote repository with all available fee
 */
class GetAllAvailableFeeByIssuerIdUseCase @Inject constructor(private val repository: FeeSelectionRepositoryImpl) {

    /**
     *  Get all available fee from remote, filter that data and returns
     *  a Resource<MutableList<DataFee>>
     *
     *  [amountValue] Amount value entered by user
     *  [paymentMethodId] Payment method selected by user
     *  [issuerId] issuer identifier
     *
     */
    suspend fun getAllAvailableFeeByIssuerId(amountValue: String, paymentMethodId: String, issuerId: String): Resource<MutableList<DataFee>> {
        return when(val response = repository.getAvailableFeeFromRemote(
            amountValue = amountValue,
            paymentMethodId = paymentMethodId,
            issuerId = issuerId)) {

            is Resource.Success -> {
                if (response.data != null) {
                    val availableFees = processRemoteData(response.data)
                    Resource.Success(availableFees)
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
     *  Receive all fee avaible from remote repository and returns a MutableList<DataFee>
     *
     *  [remoteFees] list with all fee available
     */
    private fun processRemoteData(remoteFees: List<FeeResponse>): MutableList<DataFee> {
        val dataFees = mutableListOf<DataFee>()

        remoteFees.first().payerCosts.filter {
            it.installments > 0
                    && it.recommendedMessage.isNotEmpty()
                    && it.paymentMethodOptionId.isNotEmpty()
        }.map { payerCost ->
            dataFees.add(payerCost.fromDomainToUi())
        }

        return dataFees
    }

}