package com.example.luismunoz.paymentapp.domain.model.bank

import com.example.luismunoz.paymentapp.data.source.remote.response.bank.BankResponse

fun BankResponse.fromDomainToUi() = DataBank(
    bankId = id,
    bankName = name,
    bankPath = secureThumbnail
)