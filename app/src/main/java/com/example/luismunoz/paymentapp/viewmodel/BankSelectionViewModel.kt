package com.example.luismunoz.paymentapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.luismunoz.paymentapp.domain.Resource
import com.example.luismunoz.paymentapp.domain.usecase.GetAllBanksByPaymentMethodIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 *  ViewModel that call to the remote repository and get all banks by method payment
 */
@HiltViewModel
class BankSelectionViewModel @Inject constructor(private val useCase: GetAllBanksByPaymentMethodIdUseCase): ViewModel() {

    private var _paymentMethodId = MutableLiveData<String>()

    fun getAllBanks(paymentMethodId: String) {
        _paymentMethodId.value = paymentMethodId
    }

    var bankObserver = Transformations.switchMap(_paymentMethodId) { paymentMethod ->
        liveData {
            emit(Resource.Loading)

            try {
                val response = useCase.getAllBanksByPaymentMethodId(paymentMethodId = paymentMethod)
                emit(response)
            } catch (e: Exception) {
                emit(Resource.Error(e))
            }
        }
    }

}