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

    private lateinit var _amountValue: String
    private lateinit var _paymentMethodId: String
    private lateinit var _issuerId: String

    private var _feeSelected = 0
    val feeSelected: Int
    get() = _feeSelected

    fun setFeeSelected(feeSelected: Int) {
        _feeSelected = feeSelected
    }

    fun getAllFee(amountValue: String, paymentMethodId: String, issuerId: String) {
        _amountValue = amountValue
        _paymentMethodId = paymentMethodId
        _issuerId = issuerId
    }

    val getAllFeeObserver = liveData {
        emit(Resource.Loading)

        try {
            val response = useCase
                .getAllAvailableFeeByIssuerId(
                    amountValue = _amountValue,
                    paymentMethodId = _paymentMethodId,
                    issuerId = _issuerId
                )
            emit(response)
        } catch (e: Exception) {
            emit(Resource.Error(e))
        }
    }

}