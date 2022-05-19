package com.example.luismunoz.paymentapp.data.source.remote

import com.example.luismunoz.paymentapp.data.exception.ServiceException
import com.example.luismunoz.paymentapp.data.source.remote.response.fee.FeeResponse
import com.example.luismunoz.paymentapp.data.source.remote.retrofit.FeeService
import com.example.luismunoz.paymentapp.domain.Resource
import com.example.luismunoz.paymentapp.domain.repository.FeeSelectionRepository
import javax.inject.Inject

/**
 *  Repository that get all available fee from remote
 */
class FeeSelectionRepositoryImpl @Inject constructor(private val feeService: FeeService): FeeSelectionRepository {

    /**
     *  Get all available fee from remote repository by amount, paymentMethod and issuer and returns a Resource<List<FeeResponse>?> response
     *
     *  [amountValue] amount entered by user
     *  [paymentMethodId] payment method selected by user
     *  [issuerId] issuer selected by user
     *
     */
    override suspend fun getAvailableFeeFromRemote(amountValue: String, paymentMethodId: String, issuerId: String): Resource<List<FeeResponse>?> {
        return try {
            val call = feeService.getAvailableFee(amountValue = amountValue, paymentMethodId = paymentMethodId, issuerId = issuerId)

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