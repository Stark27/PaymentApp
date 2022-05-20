package com.example.luismunoz.paymentapp.viewmodel.mock

import com.example.luismunoz.paymentapp.domain.model.DataFee

class FeeSelectionViewModelMock {

    fun getExpectedDataFeeResponse() = mutableListOf(
        DataFee(
            numberFee = 3,
            message = "3 cuotas de $ 5.985,50 ($ 17.956,50)",
            paymentMethodOptionId = "1.AQokODllZjQyNjktYjAzMy00OWU1LWJhMWQtNDE0NjQyNTM3MzY4EJaFuevHLg",
            totalAmount = 17956.5,
            feeAmount = 5985.5
        )
    )

}