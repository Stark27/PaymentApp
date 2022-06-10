package com.example.luismunoz.paymentapp.domain.usecase.mock

import com.example.luismunoz.paymentapp.data.source.remote.response.bank.BankResponse
import com.example.luismunoz.paymentapp.domain.model.bank.DataBank

class GetAllBanksByPaymentMethodIdUseCaseMock {

    fun getSuccessDataBankResponseWithInactiveValues() = arrayListOf(
        DataBank(
            bankId = "296",
            bankName = "Itau",
            bankPath =  "https://www.mercadopago.com/org-img/MP3/API/logos/master.gif"
        )
    )

    fun getSuccessDataBankResponseWithEmptyValues() = arrayListOf(
        DataBank(
            bankId = "296",
            bankName = "Itau",
            bankPath =  "https://www.mercadopago.com/org-img/MP3/API/logos/master.gif"
        )
    )

    fun getFullSuccessDataBankResponse() = arrayListOf(
        DataBank(
            bankId = "310",
            bankName = "Banco Santander",
            bankPath =  "https://www.mercadopago.com/org-img/MP3/API/logos/master.gif"
        ),
        DataBank(
            bankId = "296",
            bankName = "Itau",
            bankPath =  "https://www.mercadopago.com/org-img/MP3/API/logos/master.gif"
        )
    )

    fun getSuccessRemoteBankResponseWithInactiveValue() = arrayListOf(
        BankResponse(
            id = "310",
            name = "Banco Santander",
            secureThumbnail = "https://www.mercadopago.com/org-img/MP3/API/logos/master.gif",
            status = "inActive"
        ),
        BankResponse(
            id = "296",
            name = "Itau",
            secureThumbnail = "https://www.mercadopago.com/org-img/MP3/API/logos/master.gif",
            status = "active"
        )
    )

    fun getSuccessRemoteBankResponseWithEmptyValues() = arrayListOf(
        BankResponse(
            id = "",
            name = "Banco Santander",
            secureThumbnail = "https://www.mercadopago.com/org-img/MP3/API/logos/master.gif",
            status = "active"
        ),
        BankResponse(
            id = "296",
            name = "Itau",
            secureThumbnail = "https://www.mercadopago.com/org-img/MP3/API/logos/master.gif",
            status = "active"
        )
    )

    fun getSuccessRemoteBankResponse() = arrayListOf(
        BankResponse(
            id = "310",
            name = "Banco Santander",
            secureThumbnail = "https://www.mercadopago.com/org-img/MP3/API/logos/master.gif",
            status = "active"
        ),
        BankResponse(
            id = "296",
            name = "Itau",
            secureThumbnail = "https://www.mercadopago.com/org-img/MP3/API/logos/master.gif",
            status = "active"
        )
    )

    fun getNullRemoteBankResponse():List<BankResponse>? = null

}