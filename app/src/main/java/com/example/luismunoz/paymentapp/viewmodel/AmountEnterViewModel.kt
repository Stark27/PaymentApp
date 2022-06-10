package com.example.luismunoz.paymentapp.viewmodel

import androidx.lifecycle.*
import com.example.luismunoz.paymentapp.util.*

/**
 *  ViewModel that handle logic to validate amount value
 */
class AmountEnterViewModel: ViewModel() {

    private var _amountValidateValue: String
    val amountValidateValue: String
    get() = _amountValidateValue

    private var _validateAmount = MutableLiveData<ValidateAmountStatus>()
    val validateAmountStatus: LiveData<ValidateAmountStatus>
    get() = _validateAmount

    init {
        _amountValidateValue = Util.currencyFormat(0)
    }

    fun validateAmountObserver(amountString: String) {
        if (amountString.isEmpty() || amountString.length == MIN_AMOUNT_LENGTH) {
            _amountValidateValue = Util.currencyFormat(0)
            _validateAmount.value = ValidateAmountStatus.EMPTY_VALUE
        } else if (Util.cleanAmount(amountString).toInt() < MIN_ALLOWED_AMOUNT) {
            _amountValidateValue = Util.currencyFormat(Util.cleanAmount(amountString).toInt())
            _validateAmount.value = ValidateAmountStatus.MIN_AMOUNT
        } else if (Util.cleanAmount(amountString).toInt() > MAX_ALLOWED_AMOUNT) {
            _amountValidateValue = Util.currencyFormat(Util.cleanAmount(amountString).toInt())
            _validateAmount.value = ValidateAmountStatus.MAX_AMOUNT
        } else {
            _amountValidateValue = Util.currencyFormat(Util.cleanAmount(amountString).toInt())
            _validateAmount.value = ValidateAmountStatus.VALID_AMOUNT
        }
    }

}