package com.example.luismunoz.paymentapp.viewmodel.mock

import com.example.luismunoz.paymentapp.domain.model.bank.DataBank

class BankSelectionViewModelMock {

    fun getFullDataBankResponse() = arrayListOf(
        DataBank(
            bankId = "296",
            bankName = "Itau",
            bankPath = "https://www.mercadopago.com/org-img/MP3/API/logos/master.gif"
        )
    )
}