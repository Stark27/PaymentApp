package com.example.luismunoz.paymentapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.luismunoz.paymentapp.util.ValidateAmountStatus

/**
 *  ViewModel that handle logic to validate amount value
 */
class AmountEnterViewModel: ViewModel() {

    private var _amountValidate = MutableLiveData<Event<ValidateAmountStatus>>()
    val amountValidate: LiveData<Event<ValidateAmountStatus>>
    get() = _amountValidate

    fun validateAmountInput(amountValue: String) {
        if (amountValue.isEmpty()) {
            _amountValidate.postValue(Event(ValidateAmountStatus.EMPTY_AMOUNT))
        } else if (amountValue.toInt() == 0) {
            _amountValidate.postValue(Event(ValidateAmountStatus.ZERO_AMOUNT))
        } else {
            _amountValidate.postValue(Event(ValidateAmountStatus.VALID_AMOUNT))
        }
    }

}