package com.example.luismunoz.paymentapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.luismunoz.paymentapp.domain.Resource
import com.example.luismunoz.paymentapp.domain.usecase.GetAllAvailableFeeByIssuerIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 *  ViewModel that call to the remote repository and get all available fee
 */
@HiltViewModel
class FeeSelectionViewModel @Inject constructor(private val useCase: GetAllAvailableFeeByIssuerIdUseCase): ViewModel() {

    fun getAllFeeObserver(amountValue: String, paymentMethodId: String, issuerId: String) = liveData {
        emit(Resource.Loading)

        try {
            val response = useCase.getAllAvailableFeeByIssuerId(amountValue = amountValue, paymentMethodId = paymentMethodId, issuerId = issuerId)
            emit(response)
        } catch (e: Exception) {
            emit(Resource.Error(e))
        }
    }

}