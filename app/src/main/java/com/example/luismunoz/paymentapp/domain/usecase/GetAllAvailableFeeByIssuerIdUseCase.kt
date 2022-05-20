package com.example.luismunoz.paymentapp.domain.usecase

import com.example.luismunoz.paymentapp.data.exception.ServiceException
import com.example.luismunoz.paymentapp.data.source.remote.FeeSelectionRepositoryImpl
import com.example.luismunoz.paymentapp.data.source.remote.response.fee.FeeResponse
import com.example.luismunoz.paymentapp.domain.Resource
import com.example.luismunoz.paymentapp.domain.model.DataFee
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
     *  Receive all fee avaibles from remote repository and returns a MutableList<DataFee>
     *
     *  [remoteFees] list with all fee availables
     */
    private fun processRemoteData(remoteFees: List<FeeResponse>): MutableList<DataFee> {
        val dataFees = mutableListOf<DataFee>()
        val remoteFee = remoteFees.first()

        remoteFee.payerCosts?.forEach { payerCost ->
            if (payerCost?.installments != null
                && !payerCost.recommendedMessage.isNullOrEmpty()
                && !payerCost.paymentMethodOptionId.isNullOrEmpty()
                && payerCost.totalAmount != null
                && payerCost.installmentAmount != null) {

                dataFees.add(
                    DataFee(
                        numberFee = payerCost.installments,
                        message = payerCost.recommendedMessage,
                        paymentMethodOptionId = payerCost.paymentMethodOptionId,
                        totalAmount = payerCost.totalAmount,
                        feeAmount = payerCost.installmentAmount
                    )
                )
            }
        }

        return dataFees
    }

}