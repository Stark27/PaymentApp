package com.example.luismunoz.paymentapp.domain.usecase

import com.example.luismunoz.paymentapp.data.exception.ServiceException
import com.example.luismunoz.paymentapp.data.source.remote.PaymentSelectionRepositoryImpl
import com.example.luismunoz.paymentapp.data.source.remote.response.paymentMethod.PaymentMethodResponse
import com.example.luismunoz.paymentapp.domain.Resource
import com.example.luismunoz.paymentapp.domain.model.paymentMethod.DataPaymentMethod
import com.example.luismunoz.paymentapp.domain.model.paymentMethod.fromDomainToUi
import com.example.luismunoz.paymentapp.util.CREDIT_CARD_PAYMENT_TYPE
import com.example.luismunoz.paymentapp.util.METHOD_PAYMENT_ACTIVE
import javax.inject.Inject

/**
 *  UseCase that handle data from remote repository with all payment methods
 */
class GetPaymentMethodsUseCase @Inject constructor(private val repository: PaymentSelectionRepositoryImpl) {

    /**
     *  Get all payment methods from remote, filter that data and returns
     *  a Resource<MutableList<DataPaymentMethod>>
     */
    suspend fun getAllRemotePaymentMethod(): Resource<MutableList<DataPaymentMethod>> {
        return when(val response = repository.getPaymentMethodsFromRemote()) {
            is Resource.Success -> {
                if (response.data != null) {
                    val creditCardPayments = processRemoteData(response.data)
                    Resource.Success(creditCardPayments)
                }
                else {
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
     *  Receive all payment methods from remote repository and returns a
     *  MutableList<DataPaymentMethod> only with credit card payments
     *
     *  [paymentMethods] list with all remote payment methods
     */
    private fun processRemoteData(paymentMethods: List<PaymentMethodResponse>?): MutableList<DataPaymentMethod> {
        val creditCardPayments = mutableListOf<DataPaymentMethod>()

        paymentMethods?.filter {
            it.paymentTypeId == CREDIT_CARD_PAYMENT_TYPE
                    && it.status == METHOD_PAYMENT_ACTIVE
                    && it.id.isNotEmpty()
                    && it.name.isNotEmpty()
                    && it.secureThumbnail.isNotEmpty()
        }?.map {
                paymentMethod -> creditCardPayments.add(paymentMethod.fromDomainToUi())
        }

        return creditCardPayments
    }

}