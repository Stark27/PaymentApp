package com.example.luismunoz.paymentapp.domain.usecase.mock

import com.example.luismunoz.paymentapp.data.source.remote.response.fee.FeeResponse
import com.example.luismunoz.paymentapp.data.source.remote.response.fee.PayerCostsItem
import com.example.luismunoz.paymentapp.domain.model.DataFee

class GetAllAvailableFeeByIssuerIdUseCaseMock {

    fun getFullDataFee() = arrayListOf(
        DataFee(
            numberFee = 1,
            message = "1 cuota de $ 15.000,00 ($ 15.000,00)",
            paymentMethodOptionId = "1.AQokODllZjQyNjktYjAzMy00OWU1LWJhMWQtNDE0NjQyNTM3MzY4EJaFuevHLg",
            totalAmount = 15000.0,
            feeAmount = 15000.0
        ),
        DataFee(
            numberFee = 3,
            message = "3 cuotas de $ 5.985,50 ($ 17.956,50)",
            paymentMethodOptionId = "1.AQokODllZjQyNjktYjAzMy00OWU1LWJhMWQtNDE0NjQyNTM3MzY4EJaFuevHLg",
            totalAmount = 17956.5,
            feeAmount = 5985.5
        )
    )

    fun getEmptyValuesDataFee() = arrayListOf(
        DataFee(
            numberFee = 3,
            message = "3 cuotas de $ 5.985,50 ($ 17.956,50)",
            paymentMethodOptionId = "1.AQokODllZjQyNjktYjAzMy00OWU1LWJhMWQtNDE0NjQyNTM3MzY4EJaFuevHLg",
            totalAmount = 17956.5,
            feeAmount = 5985.5
        )
    )

    fun getEmptyValuesRemoteRepositoryResponse() = arrayListOf(
        FeeResponse(
            payerCosts = arrayListOf(
                PayerCostsItem(
                    installments = null,
                    recommendedMessage = "1 cuota de $ 15.000,00 ($ 15.000,00)",
                    paymentMethodOptionId = "1.AQokODllZjQyNjktYjAzMy00OWU1LWJhMWQtNDE0NjQyNTM3MzY4EJaFuevHLg",
                    totalAmount = 15000.0,
                    installmentAmount = 15000.0
                ),
                PayerCostsItem(
                    installments = 3,
                    recommendedMessage = "3 cuotas de $ 5.985,50 ($ 17.956,50)",
                    paymentMethodOptionId = "1.AQokODllZjQyNjktYjAzMy00OWU1LWJhMWQtNDE0NjQyNTM3MzY4EJaFuevHLg",
                    totalAmount = 17956.5,
                    installmentAmount = 5985.5
                )
            )
        )
    )

    fun getFullRemoteRepositoryResponse() = arrayListOf(
        FeeResponse(
            payerCosts = arrayListOf(
                PayerCostsItem(
                    installments = 1,
                    recommendedMessage = "1 cuota de $ 15.000,00 ($ 15.000,00)",
                    paymentMethodOptionId = "1.AQokODllZjQyNjktYjAzMy00OWU1LWJhMWQtNDE0NjQyNTM3MzY4EJaFuevHLg",
                    totalAmount = 15000.0,
                    installmentAmount = 15000.0
                ),
                PayerCostsItem(
                    installments = 3,
                    recommendedMessage = "3 cuotas de $ 5.985,50 ($ 17.956,50)",
                    paymentMethodOptionId = "1.AQokODllZjQyNjktYjAzMy00OWU1LWJhMWQtNDE0NjQyNTM3MzY4EJaFuevHLg",
                    totalAmount = 17956.5,
                    installmentAmount = 5985.5
                )
            )
        )
    )

    fun getNullRemoteRepositoryResponse(): List<FeeResponse>? = null

}