package com.example.luismunoz.paymentapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.luismunoz.paymentapp.domain.Resource
import com.example.luismunoz.paymentapp.domain.usecase.GetPaymentMethodsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 *  ViewModel that call to the remote repository and get all payment methods
 */
@HiltViewModel
class PaymentSelectionViewModel @Inject constructor(private val useCase: GetPaymentMethodsUseCase) : ViewModel() {

    /**
     * Observer that emit a success or error response given by repository response
     */
    fun paymentMethodObserver() = liveData {
            emit(Resource.Loading)

            try {
                val response = useCase.getAllRemotePaymentMethod()
                emit(response)
            } catch (e: Exception) {
                emit(Resource.Error(e))
            }
        }

}