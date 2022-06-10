package com.example.luismunoz.paymentapp.viewmodel

import androidx.lifecycle.*
import com.example.luismunoz.paymentapp.domain.Resource
import com.example.luismunoz.paymentapp.domain.usecase.GetAllBanksByPaymentMethodIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 *  ViewModel that call to the remote repository and get all banks by method payment
 */
@HiltViewModel
class BankSelectionViewModel @Inject constructor(private val useCase: GetAllBanksByPaymentMethodIdUseCase) : ViewModel() {

    private lateinit var _paymentMethodId: String

    fun getAllBanks(paymentMethodId: String) {
        _paymentMethodId = paymentMethodId
    }

    var bankObserver = liveData {
        emit(Resource.Loading)

        try {
            val response = useCase.getAllBanksByPaymentMethodId(paymentMethodId = _paymentMethodId)
            emit(response)
        } catch (e: Exception) {
            emit(Resource.Error(e))
        }
    }

}