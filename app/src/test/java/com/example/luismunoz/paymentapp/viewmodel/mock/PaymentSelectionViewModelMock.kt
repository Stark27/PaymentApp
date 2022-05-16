package com.example.luismunoz.paymentapp.viewmodel.mock

import com.example.luismunoz.paymentapp.domain.model.DataPaymentMethod

class PaymentSelectionViewModelMock {

    fun getExpectedResponse() = arrayListOf(
        DataPaymentMethod(
            paymentMethodId = "visa",
            paymentMethodName = "Visa",
            paymentMethodPath = "path"
        )
    )
}