package com.example.luismunoz.paymentapp.data.source.remote

import com.example.luismunoz.paymentapp.data.exception.ServiceException
import com.example.luismunoz.paymentapp.data.source.remote.response.paymentMethod.PaymentMethodResponse
import com.example.luismunoz.paymentapp.data.source.remote.retrofit.PaymentMethodService
import com.example.luismunoz.paymentapp.domain.Resource
import com.example.luismunoz.paymentapp.domain.repository.PaymentSelectionRepository
import javax.inject.Inject

/**
 *  Repository that get all payment method from remote
 */
class PaymentSelectionRepositoryImpl @Inject constructor(private val paymentMethodService: PaymentMethodService): PaymentSelectionRepository {

    /**
     *  Get all payment method from remote and returns a Resource<List<PaymentMethodResponse>?> response
     */
    override suspend fun getPaymentMethodsFromRemote(): Resource<List<PaymentMethodResponse>?> {
        return try {
            val call = paymentMethodService.getPaymentMethod()

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